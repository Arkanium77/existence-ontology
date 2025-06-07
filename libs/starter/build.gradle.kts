group = "team.isaz.existence"
version = "1.0.0"
description = "Spring Boot starter for Existence Ontology"

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
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

    implementation("team.isaz.existence:core:1.0.0")
}
