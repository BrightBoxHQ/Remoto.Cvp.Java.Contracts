import com.google.protobuf.gradle.*

group = "remoto.cvp.services"
version = "4.9.9"

dependencies {
    implementation("io.grpc:grpc-protobuf:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:grpc-netty-shaded:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:grpc-stub:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:protoc-gen-grpc-kotlin:${rootProject.ext["grpcKotlinVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

//    api("com.google.protobuf:protobuf-java-util:$protocVersion")
//    api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlinVersion"]}")
}

protobuf {
    generatedFilesBaseDir = "$projectDir/gen"
    protoc {
        artifact = "com.google.protobuf:protoc:${rootProject.ext["protocVersion"]}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.ext["protobufVersion"]}"
        }
        id("grpckt") {
            // from https://github.com/grpc/grpc-kotlin/blob/master/examples/stub/build.gradle.kts
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${rootProject.ext["grpcKotlinVersion"]}:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}
