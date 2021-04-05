import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

group = "remoto.cvp.uapi.events"
version = "4.7.6"

protobuf {
    generatedFilesBaseDir = "$projectDir/gen"
    protoc {
        artifacts {
            artifact = "com.google.protobuf:protoc:${rootProject.ext["protocVersion"]}"
        }
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

