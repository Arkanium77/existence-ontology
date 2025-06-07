plugins {
    java
    `maven-publish`
    signing
}

group = "team.isaz.existence"
version = "0.1.0-SNAPSHOT"
description = "Existence Ontology core utilities"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}
val ossrhUsername: String? = System.getenv("OSSRH_USERNAME")
val ossrhPassword: String? = System.getenv("OSSRH_PASSWORD")
val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
val signingPassword: String? = System.getenv("GPG_PASSWORD")

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.mockito:mockito-core:5.18.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
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

    // Добавляем только если есть логин и пароль
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

    // Проверка при выполнении publish задач
    tasks.matching { it.name.startsWith("publish") }.configureEach {
        doFirst {
            require(!ossrhUsername.isNullOrBlank()) { "OSSRH_USERNAME not set" }
            require(!ossrhPassword.isNullOrBlank()) { "OSSRH_PASSWORD not set" }
        }
    }
}

signing {
    isRequired = gradle.taskGraph.hasTask("publish")

    if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications["mavenJava"])
    } else {
        logger.lifecycle("Skipping signing: GPG_PRIVATE_KEY or GPG_PASSWORD not set")
    }
}
