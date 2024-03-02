plugins {
    `kotlin-dsl`
    java
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.6")
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:6.0.7")
    implementation("gradle.plugin.org.flywaydb:gradle-plugin-publishing:10.8.1")
    implementation("org.flywaydb:flyway-mysql:10.8.1")

}

tasks.register("installGitHook", Copy::class) {
    from(rootProject.file("scripts/pre-commit"))
    into(rootProject.file(".git/hooks.git/hooks"))
    fileMode = 0b0111101101
}