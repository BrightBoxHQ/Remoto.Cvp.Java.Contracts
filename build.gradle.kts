import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    idea
    `maven-publish`
    id("com.google.protobuf") version "0.8.15"
}

val protobufVersion by extra("1.36.0")
val grpcKotlinVersion by extra("1.0.0")
val protocVersion by extra("3.15.6")

group = "remoto.cvp"

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.google.protobuf")
        plugin("org.gradle.maven-publish")
    }

    dependencies {
        implementation("com.google.protobuf:protobuf-java:3.11.1")
    }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(15)) }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "15"
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = project.name
                from(components["java"])
            }
        }
        repositories {
            maven("https://pkgs.dev.azure.com/bright-box-azure/_packaging/autotests/maven/v1") {
                credentials {
                    username = "bright-box-azure"
                    val azureAutotestsToken: String by project
                    password = azureAutotestsToken
                }
            }
        }
    }

    sourceSets {
        main {
            proto {
                srcDir("src/main/proto")
            }
        }
    }

    protobuf {
        generatedFilesBaseDir = "$projectDir/gen"
        protoc {
            artifact = "com.google.protobuf:protoc:$protocVersion"
        }
        generateProtoTasks {
            all().forEach {
                it.builtins { }
            }
        }
    }

    tasks.withType<Delete> {
        delete(protobuf.protobuf.generatedFilesBaseDir)
    }
}
