plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js {
        nodejs {
            useEsModules()
        }
        binaries.executable()
    }

    sourceSets {
        js {
            nodejs {
                testTask {
                    useMocha {
                        timeout = "60000"
                    }
                }
            }
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}


