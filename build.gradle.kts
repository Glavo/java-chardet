plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
    id("org.glavo.compile-module-info-plugin") version "2.0"
    id("org.glavo.load-maven-publish-properties") version "0.1.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
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


java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
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

tasks.withType<Javadoc>().configureEach {
    (options as StandardJavadocDocletOptions).also {
        it.encoding("UTF-8")
        it.addStringOption("link", "https://docs.oracle.com/en/java/javase/21/docs/api/")
        it.addBooleanOption("html5", true)
        it.addStringOption("Xdoclint:none", "-quiet")
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            version = project.version.toString()
            artifactId = project.name
            from(components["java"])

            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/Glavo/java-chardet")

                licenses {
                    license {
                        name.set("Mozilla Public License Version 1.1")
                        url.set("https://www.mozilla.org/en-US/MPL/1.1")
                    }
                    license {
                        name.set("GENERAL PUBLIC LICENSE, version 3 (GPL-3.0)")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                    license {
                        name.set("GNU LESSER GENERAL PUBLIC LICENSE, version 3 (LGPL-3.0)")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("albfernandez")
                        name.set("Alberto Fernández")
                        email.set("infjaf@gmail.com")
                        organization.set("Alberto Fernández")
                        organizationUrl.set("https://github.com/albfernandez/")
                    }
                    developer {
                        id.set("glavo")
                        name.set("Glavo")
                        email.set("zjx001202@gmail.com")
                        url.set("https://github.com/Glavo")
                    }
                }

                scm {
                    url.set("https://github.com/Glavo/java-chardet")
                }

                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/Glavo/java-chardet/issues")
                }
            }
        }
    }
}

if (rootProject.ext.has("signing.key")) {
    signing {
        useInMemoryPgpKeys(
            rootProject.ext["signing.keyId"].toString(),
            rootProject.ext["signing.key"].toString(),
            rootProject.ext["signing.password"].toString(),
        )
        sign(publishing.publications["maven"])
    }
}

// ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(rootProject.ext["sonatypeStagingProfileId"].toString())
            username.set(rootProject.ext["sonatypeUsername"].toString())
            password.set(rootProject.ext["sonatypePassword"].toString())
        }
    }
}
