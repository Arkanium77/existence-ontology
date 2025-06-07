plugins {
    java
    `maven-publish`
    signing
    id("io.spring.dependency-management") version "1.1.4"
}

group = "team.isaz.existence"
version = "0.1.0-SNAPSHOT"
description = "Spring Boot starter for Existence Ontology"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.0")
        mavenBom("org.junit:junit-bom:5.12.2")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    implementation("team.isaz.existence:core:0.1.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
