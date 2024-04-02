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

        register("androidLibrary") {
            id = "sopt.android.library"
            implementationClass = "AndroidLibraryPlugin"
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
    }
}