plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.mybatis:mybatis:3.5.19")
    runtimeOnly("com.mysql:mysql-connector-j:8.0.33")
}

tasks.test {
    useJUnitPlatform()
}