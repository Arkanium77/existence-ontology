plugins {
    id("java")
    id("maven-publish")
    id("signing")
    id("io.spring.dependency-management") version "1.1.4"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.0")
            mavenBom("org.junit:junit-bom:5.12.2")
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
        withSourcesJar()
        withJavadocJar()
    }

    tasks.test {
        useJUnitPlatform()
    }

    val ossrhUsername: String? = System.getenv("OSSRH_USERNAME")
    val ossrhPassword: String? = System.getenv("OSSRH_PASSWORD")
    val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
    val signingPassword: String? = System.getenv("GPG_PASSWORD")

    afterEvaluate {
        extensions.findByType(PublishingExtension::class.java)?.apply {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])
                    pom {
                        name.set(project.name)
                        description.set(project.description)
                        url.set("https://github.com/Arkanium77/existence-ontology")
                        licenses {
                            license {
                                name.set("Apache License, Version 2.0")
                                url.set("https://www.apache.org/licenses/LICENSE-2.0")
                                distribution.set("repo")
                            }
                        }
                        developers {
                            developer {
                                id.set("Arkanium77")
                                name.set("Arkanium77")
                                email.set("arkanium77@gmail.com")
                            }
                        }
                        scm {
                            connection.set("scm:git:https://github.com/Arkanium77/existence-ontology.git")
                            developerConnection.set("scm:git:git@github.com:Arkanium77/existence-ontology.git")
                            url.set("https://github.com/Arkanium77/existence-ontology")
                        }
                    }
                }
            }

            if (!ossrhUsername.isNullOrBlank() && !ossrhPassword.isNullOrBlank()) {
                repositories {
                    maven {
                        name = "Sonatype"
                        url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        credentials {
                            username = ossrhUsername
                            password = ossrhPassword
                        }
                    }
                }
            }
        }

        extensions.findByType(SigningExtension::class.java)?.apply {
            isRequired = gradle.taskGraph.hasTask("publish")
            if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
                useInMemoryPgpKeys(signingKey, signingPassword)
                sign(extensions.getByType<PublishingExtension>().publications["mavenJava"])
            } else {
                logger.lifecycle("Skipping signing: GPG_PRIVATE_KEY or GPG_PASSWORD not set")
            }
        }

        tasks.matching { it.name.startsWith("publish") && !it.name.contains("MavenLocal") }.configureEach {
            doFirst {
                require(!ossrhUsername.isNullOrBlank()) { "OSSRH_USERNAME not set" }
                require(!ossrhPassword.isNullOrBlank()) { "OSSRH_PASSWORD not set" }
            }
        }
    }
}
