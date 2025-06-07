// Root build script for shared configuration

import org.gradle.plugins.signing.SigningExtension

subprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("maven-publish") {
        val ossrhUsername: String? = System.getenv("OSSRH_USERNAME")
        val ossrhPassword: String? = System.getenv("OSSRH_PASSWORD")

        extensions.getByType<org.gradle.api.publish.PublishingExtension>().apply {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])
                    pom {
                        name.set(project.name)
                        description.set(project.description)
                        url.set("https://github.com/Arkanium77/ExistenceOntology")
                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("https://opensource.org/licenses/MIT")
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
                            connection.set("scm:git:git://github.com/Arkanium77/ExistenceOntology.git")
                            developerConnection.set("scm:git:ssh://github.com:Arkanium77/ExistenceOntology.git")
                            url.set("https://github.com/Arkanium77/ExistenceOntology")
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

            tasks.matching { it.name.startsWith("publish") }.configureEach {
                doFirst {
                    require(!ossrhUsername.isNullOrBlank()) { "OSSRH_USERNAME not set" }
                    require(!ossrhPassword.isNullOrBlank()) { "OSSRH_PASSWORD not set" }
                }
            }
        }
    }

    plugins.withId("signing") {
        val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
        val signingPassword: String? = System.getenv("GPG_PASSWORD")

        extensions.getByType<SigningExtension>().run {
            isRequired = gradle.taskGraph.hasTask("publish")
            if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
                useInMemoryPgpKeys(signingKey, signingPassword)
                sign(extensions.getByType<org.gradle.api.publish.PublishingExtension>().publications["mavenJava"])
            } else {
                logger.lifecycle("Skipping signing: GPG_PRIVATE_KEY or GPG_PASSWORD not set")
            }
        }
    }
}
