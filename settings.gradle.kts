dependencyResolutionManagement {
    repositories {
        mavenCentral()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/gradle/gradle-plugins")
    }
}

rootProject.name = "cvp-java-contracts"

include("device-protocol", "uapi-events", "vehicle-state-tracking")
