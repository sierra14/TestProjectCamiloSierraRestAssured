plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("io.rest-assured:rest-assured:4.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")

}

tasks.test {
    useJUnitPlatform()
    include("**/*Tests.class")
}