import com.google.protobuf.gradle.*

group = "remoto.cvp.services"
version = "4.9.9"

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

tasks.withType<Delete> {
    delete(protobuf.protobuf.generatedFilesBaseDir)
}
