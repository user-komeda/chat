import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-common-conventions")
    id("spring-conventions")
    id("static-analysis")
}


dependencies {
}
tasks.getByName<BootJar>("bootJar") {
    enabled = false  // ルートプロジェクトで実行Jarを作成しない設定
}

tasks.getByName<Jar>("jar") {
    enabled = true  // ルートプロジェクトでJarを作成しない設定
}
