plugins {
    `java-library`
    `maven-publish`
    signing
    jacoco
    id("me.champeau.jmh") version "0.7.3"
    id("org.sonarqube") version "7.3.1.8318"
    id("pl.allegro.tech.build.axion-release") version "1.21.2"
    id("com.adarshr.test-logger") version "4.0.0"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    jmh("nl.basjes.parse.useragent:yauaa:8.1.1")
}

dependencyLocking {
    lockAllConfigurations()
}

group = "com.github.bgalek.utils"
version = scmVersion.version

java {
    withSourcesJar()
    withJavadocJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<JavaCompile>("compileJava") {
    options.release.set(11)
}

tasks {
    jar {
        manifest {
            attributes(mapOf("Implementation-Title" to project.name, "Implementation-Version" to project.version))
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}

publishing {
    publications {
        create<MavenPublication>("sonatype") {
            artifactId = "devicedetect"
            from(components["java"])
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
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
        }
    }
}

System.getenv("GPG_KEY_ID")?.let {
    signing {
        useInMemoryPgpKeys(
            System.getenv("GPG_KEY_ID"),
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PRIVATE_KEY_PASSWORD")
        )
        sign(publishing.publications)
    }
}
