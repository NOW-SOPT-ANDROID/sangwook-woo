plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.library.compose)
}

android {
    namespace = "org.sopt.core.ui"
}

dependencies {
    implementation(projects.core.model)
}