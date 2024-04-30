plugins {
    id("java")
    id("org.glavo.compile-module-info-plugin") version "2.0"
}

group = "org.glavo"
version = "2.4.0-beta1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.compileJava {
    options.release.set(8)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}

tasks.compileModuleInfo {
    moduleVersion = project.version.toString()
}
