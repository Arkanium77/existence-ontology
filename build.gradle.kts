plugins {
    id("java")
    id("signing")
    id("maven-publish")
    id("io.spring.dependency-management") version "1.1.4"
    id ("tech.yanand.maven-central-publish") version "1.3.0"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "signing")
    apply(plugin = "maven-publish")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "tech.yanand.maven-central-publish")

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

    val signingKeyPath = System.getenv("GPG_KEY_FILE")
    val signingPassword: String? = System.getenv("GPG_PASSWORD")
    val sonatypeToken: String? = System.getenv("SONATYPE_TOKEN")
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
        }

        // 2. Настройка signing после публикации
        extensions.findByType<SigningExtension>()?.apply {
            isRequired = gradle.taskGraph.hasTask("publish") || gradle.taskGraph.hasTask("jreleaserFullRelease")
            if (!signingKeyPath.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
                val key = file(signingKeyPath).readText()
                useInMemoryPgpKeys(key, signingPassword)
                sign(publishing.publications["mavenJava"])
            } else {
                logger.warn("GPG_PRIVATE_KEY or GPG_PASSWORD not set, skipping signing")
            }
        }

        // 3. Настройка публикации в Maven Central
        extensions.findByName("mavenCentral")?.let { ext ->
            (ext as groovy.lang.GroovyObject).setProperty("authToken", sonatypeToken)
            ext.setProperty("publishingType", "USER_MANAGED")
            ext.setProperty("maxWait", 60)
        }
    }
}
