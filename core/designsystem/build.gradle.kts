@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.library.compose)
}

android {
    namespace = "com.sopt.core.designsystem"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.ui)
}