kotlin {
    js {
        useCommonJs()
//        binaries.executable()
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
