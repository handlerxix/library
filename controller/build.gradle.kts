plugins {
    java
}

group = "org.example.demin.controller"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":models"))
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.google.inject:guice:4.0")

    testImplementation("junit:junit:4.12")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("org.mockito:mockito-core:4.0.0")
}

task<JavaExec>("startApplication") {
    standardInput = System.`in`
    group = "launch"
    workingDir = rootProject.projectDir
    classpath = sourceSets["main"].runtimeClasspath
    main = "org.example.demin.controller.ApplicationMain"
}