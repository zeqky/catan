plugins {
    kotlin("jvm") version "1.9.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

extra.apply {
    set("pluginName", project.name.split('-').joinToString("") { it.capitalize() })
    set("packageName", project.name.replace("-", ""))
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.github.monun:tap-api:4.9.8")
    implementation("io.github.monun:kommand-api:3.1.7")
    implementation("io.github.monun:heartbeat-coroutines:0.0.5")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }
}