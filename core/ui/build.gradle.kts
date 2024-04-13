plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.hilt)
}

android {
    namespace = "org.sopt.ui"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.google.material)
    implementation(libs.timber)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.bundles.orbit)
}