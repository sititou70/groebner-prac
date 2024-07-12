import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

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
        
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}


