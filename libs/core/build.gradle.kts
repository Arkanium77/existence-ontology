plugins {
    java
    `maven-publish`
    signing
}

group = "team.isaz.existence"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.mockito:mockito-core:4.11.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("core")
                description.set("Existence Ontology core utilities")
                url.set("https://github.com/Arkanium77/ExistenceOntology")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        // developer ID обычно совпадает с ником
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
    repositories {
        maven {
            name = "Sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        project.findProperty("signingKey") as String? ?: System.getenv("GPG_PRIVATE_KEY"),
        project.findProperty("signingPassword") as String? ?: System.getenv("GPG_PASSWORD")
    )
    sign(publishing.publications["mavenJava"])
}
