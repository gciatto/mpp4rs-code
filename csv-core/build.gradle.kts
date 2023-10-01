kotlin {
    js {
        useEsModules()
        binaries.executable()
        nodejs()
    }
    sourceSets {
        commonMain {
            dependencies {
                // put your common main dependencies here
            }
        }
        commonTest {
            dependencies {
                // put your common test dependencies here
            }
        }

        getByName("jvmMain") {
            dependencies {
                // put your JVM main dependencies here
            }
        }

        getByName("jsMain") {
            dependencies {
                // put your JVM test dependencies here
            }
        }
    }
}

shadowJar()

tasks.create<Copy>("exportJarForPython") {
    group = "python"
    val shadowJar by tasks.getting(Jar::class)
    dependsOn(shadowJar)
    from(shadowJar.archiveFile) {
        rename(".*?\\.jar", "csv.jar")
    }
    into(rootProject.projectDir.resolve("csv-python/jcsv/jvm"))
}
