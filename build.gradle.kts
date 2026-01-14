plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.snipr"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set("AFKPlugin")
    }
    
    build {
        dependsOn(shadowJar)
    }
}
