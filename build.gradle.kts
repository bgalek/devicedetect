import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
    `java-library`
    `maven-publish`
    signing
    jacoco
    id("me.champeau.gradle.jmh") version "0.5.3"
    id("pl.allegro.tech.build.axion-release") version "1.15.5"
    id("com.adarshr.test-logger") version "3.2.0"
    id("org.sonarqube") version "4.4.1.3373"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    jmh("nl.basjes.parse.useragent:yauaa:7.22.0")
}

group = "com.github.bgalek.utils"
version = scmVersion.version

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name, "Implementation-Version" to project.version))
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = setOf(FAILED, SKIPPED)
    }
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

jacoco {
    toolVersion = "0.8.6"
    reportsDir = file("$buildDir/reports/jacoco")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/report.xml")
        csv.isEnabled = false
        html.isEnabled = false
    }
}

publishing {
    publications {
        create<MavenPublication>("sonatype") {
            artifactId = "devicedetect"
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("devicedetect")
                description.set("Java port of varnish-devicedetect")
                url.set("https://github.com/bgalek/devicedetect/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("bgalek")
                        name.set("Bartosz Gałek")
                        email.set("bartosz@galek.com.pl")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/bgalek/devicedetect.git")
                    developerConnection.set("scm:git:ssh://github.com:bgalek/devicedetect.git")
                    url.set("https://github.com/bgalek/devicedetect/")
                }
            }
        }
    }
    repositories {
        maven {
            credentials {
                username = project.properties["ossrhUsername"] as String?
                password = project.properties["ossrhPassword"] as String?
            }
            val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["sonatype"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
