plugins {
    `kotlin-dsl`
}

group = "org.sopt.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "sopt.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidApplicationCompose") {
            id = "sopt.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }

        register("androidLibrary") {
            id = "sopt.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidLibraryCompose") {
            id = "sopt.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }

        register("androidHilt") {
            id = "sopt.android.hilt"
            implementationClass = "HiltPlugin"
        }

        register("javaLibrary") {
            id = "sopt.java.library"
            implementationClass = "JavaLibraryPlugin"
        }

        register("FeaturePlugin") {
            id = "sopt.plugin.feature"
            implementationClass = "FeaturePlugin"
        }

        register("RoomPlugin") {
            id = "sopt.plugin.room"
            implementationClass = "RoomPlugin"
        }

        register("DataPlugin") {
            id = "sopt.plugin.data"
            implementationClass = "DataPlugin"
        }

        register("AndroidTest") {
            id = "sopt.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("UnitTest") {
            id = "sopt.plugin.test"
            implementationClass = "UnitTestPlugin"
        }
    }
}
