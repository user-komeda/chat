import com.github.spotbugs.snom.SpotBugsTask

plugins {
    id("pmd")
    id("checkstyle")
    id("com.github.spotbugs")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    spotbugsPlugins("com.mebigfatguy.sb-contrib:sb-contrib:7.6.4")

}

checkstyle {
    toolVersion = "10.13.0"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
    maxWarnings = 0
}

pmd {
    isConsoleOutput = true
    toolVersion = "7.0.0-rc4"
    ruleSetFiles = rootProject.files("config/pmd/pmd.xml")
    isIgnoreFailures = false
}

spotbugs {
    ignoreFailures = false
    showStackTraces = true
    showProgress = true
    excludeFilter = rootProject.file("config/spotbugs/excludeFilter.xml")
}
tasks.withType<SpotBugsTask>().configureEach {
    reports.create("html") {
        required = true
        setStylesheet("fancy-hist.xsl")
    }
    reports.create("xml") {
        required = true
    }
}