package org.sopt.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Const.compileSdk

        defaultConfig {
            minSdk = Const.minSdk

            vectorDrawables.useSupportLibrary = true

            buildConfigField(
                type = "String",
                name = "AUTH_BASE_URL",
                gradleLocalProperties(rootDir, providers).getProperty("auth.base.url")
            )
            buildConfigField(
                type = "String",
                name = "REQRES_BASE_URL",
                gradleLocalProperties(rootDir, providers).getProperty("reqres.base.url")
            )
        }

        compileOptions {
            sourceCompatibility = Const.JAVA_VERSION
            targetCompatibility = Const.JAVA_VERSION
        }

        kotlinOptions {
            jvmTarget = Const.JAVA_VERSION.toString()
        }

        buildTypes {
            getByName("debug") {
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-debug.pro",
                )
            }

            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro",
                )
            }
        }

        buildFeatures {
            buildConfig = true
        }
    }
}

internal fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(
    block: KotlinJvmOptions.() -> Unit,
) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}