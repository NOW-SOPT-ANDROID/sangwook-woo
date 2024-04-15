@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.android.application)
    alias(libs.plugins.sopt.android.application.compose)
    alias(libs.plugins.sopt.android.hilt)
}

android {
    namespace = "org.sopt.now"

    defaultConfig {
        applicationId = "com.sopt.now"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.datastore)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.feature.main)
}