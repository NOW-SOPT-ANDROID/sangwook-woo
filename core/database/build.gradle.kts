plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.hilt)
    alias(libs.plugins.sopt.plugin.room)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.database"
}

dependencies {
}