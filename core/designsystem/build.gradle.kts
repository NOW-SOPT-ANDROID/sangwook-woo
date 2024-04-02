@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.designsystem"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.google.material)
    implementation(libs.coil)
}