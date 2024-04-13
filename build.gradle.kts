import org.gradle.api.file.DuplicatesStrategy.INCLUDE

plugins {
    kotlin("jvm") version "1.9.23"
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.9")
    implementation("io.ktor:ktor-server-netty:2.3.9")
    implementation("io.ktor:ktor-server-html-builder:2.3.9")
    implementation("ch.qos.logback:logback-classic:1.5.3")

    testImplementation("io.ktor:ktor-server-test-host:2.3.9")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.23")
}

task<JavaExec>("run") {
    classpath = files(tasks.jar)
}

tasks {
    jar {
        manifest {
            attributes("Main-Class" to "io.initialcapacity.streaming.ApplicationKt")
        }

        duplicatesStrategy = INCLUDE

        from({
            configurations.runtimeClasspath.get()
                    .filter { it.name.endsWith("jar") }
                    .map {
                        zipTree(it)
                    }
        })
    }
}
