import com.google.protobuf.gradle.proto

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

    if (name == "vehicle-state-tracking") {
        dependencies {
            implementation("io.grpc:grpc-protobuf:$protobufVersion")
            implementation("io.grpc:grpc-netty-shaded:$protobufVersion")
            implementation("io.grpc:grpc-stub:$protobufVersion")
            implementation("io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
            implementation("javax.annotation:javax.annotation-api:1.3.2")

//    api("com.google.protobuf:protobuf-java-util:$protocVersion")
//    api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
            implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
        }
    }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(15)) }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
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
}
