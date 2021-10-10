plugins {
    java
    kotlin("jvm") version "1.4.31"
}

group = "org.example.demin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val lombok = "org.projectlombok:lombok:1.18.4"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        compileOnly("com.intellij:annotations:12.0")
        compileOnly(lombok)

        annotationProcessor(lombok)
    }
}