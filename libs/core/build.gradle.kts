plugins {
    java
    `maven-publish`
    signing
    id("io.spring.dependency-management") version "1.1.4"
}

group = "team.isaz.existence"
version = "0.1.0-SNAPSHOT"
description = "Existence Ontology core utilities"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<Javadoc>().configureEach {
    (options as? StandardJavadocDocletOptions)?.addStringOption("Xdoclint:none", "-quiet")
    isFailOnError = false
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
    options.compilerArgs.add("-Xlint:-options")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.junit:junit-bom:5.12.2")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.mockito:mockito-core:5.18.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
