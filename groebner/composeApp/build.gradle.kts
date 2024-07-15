plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
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
