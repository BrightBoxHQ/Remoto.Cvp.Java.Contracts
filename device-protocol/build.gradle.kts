plugins {
    // https://github.com/davidmc24/gradle-avro-plugin
    id("com.github.davidmc24.gradle.plugin.avro") version "1.1.0"
//    id("com.github.davidmc24.gradle.plugin.avro-base") version "1.1.0"
}


group = "remoto.cvp.device.protocol"
//version '1.0-SNAPSHOT'
version = "4.7.4"

val avroVersion = "1.10.1"

dependencies {
    implementation("org.apache.avro:avro:$avroVersion")
//    implementation("org.apache.avro:avro-compiler:$avroVersion")
    implementation("org.apache.avro:avro-ipc:$avroVersion")
}

avro {
}

/*tasks.withType<GenerateAvroJavaTask> {
    source("src/main/avro")
    setOutputDir(file("$projectDir/gen/main/avro"))
}*/

//compileJava.source(generateAvro.outputs)

