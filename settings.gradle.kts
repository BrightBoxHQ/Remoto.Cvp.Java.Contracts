rootProject.name = "cvp-java-contracts"
include("uapi-events", "vehicle-state-tracking")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        jcenter()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/gradle/gradle-plugins")
    }
}
