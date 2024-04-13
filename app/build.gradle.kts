@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.android.application)
    alias(libs.plugins.sopt.android.hilt)
}

android {
    namespace = "com.sopt.now"

    defaultConfig {
        applicationId = "com.sopt.now"
        versionCode = 1
        versionName = "1.0"
    }
    viewBinding.enable = true
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.feature.main)
    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.mypage)
}