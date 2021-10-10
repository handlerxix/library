plugins {
    java
    application
}

group = "org.example.demin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":models"))
    implementation("com.google.code.gson:gson:2.8.8")
}

task<JavaExec>("startApplication") {
    standardInput = System.`in`
    group = "launch"
    workingDir = rootProject.projectDir
    classpath = sourceSets["main"].runtimeClasspath
    main = "org.example.demin.controller.ApplicationMain"
}