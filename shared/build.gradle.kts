
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
}

kotlin {
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

