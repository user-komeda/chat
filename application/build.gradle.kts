plugins {
    id("spring-conventions")
    id("java-common-conventions")
    id("static-analysis")
}


dependencies {
    implementation(project(":domain"))
    runtimeOnly(project(":infratecture"))
}