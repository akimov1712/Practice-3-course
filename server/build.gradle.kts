plugins {
    application
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    id("kotlinx-serialization")
}

group = "ru.donnu.practice"
version = "1.0.0"
application {
    mainClass.set("ru.donnu.practice.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {

    val logback_version= "1.5.13"
    val exposed_version="0.53.0"
    val h2_version="2.2.224"

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}