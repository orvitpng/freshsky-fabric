object Constants {
    const val MINECRAFT_VERSION = "1.20.4"
    const val FABRIC_LOADER_VERSION = "0.15.10"
    const val FABRIC_API_VERSION = "0.97.0+1.20.4"

    const val VERSION = "0.1.0"
}

plugins {
    id("fabric-loom") version "1.6-SNAPSHOT"
}

base {
    group = "ninja.carter.mods"
    archivesName = "freshsky-fabric"
    version = Constants.VERSION
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = Constants.MINECRAFT_VERSION)
    modImplementation(
        group = "net.fabricmc",
        name = "fabric-loader",
        version = Constants.FABRIC_LOADER_VERSION,
    )
    mappings(loom.officialMojangMappings())

    fun addEmbeddedFabricModule(name: String) {
        val module = fabricApi.module(name, Constants.FABRIC_API_VERSION)
        modImplementation(module)
        include(module)
    }

    addEmbeddedFabricModule("fabric-lifecycle-events-v1")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }
}
// TODO: combine this with the above tasks block
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
