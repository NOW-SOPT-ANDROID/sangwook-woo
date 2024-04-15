@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.sopt.android.hilt)
    alias(libs.plugins.sopt.android.test)
}

android {
    namespace = "org.sopt.common"
}

dependencies {
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.coroutine)
    implementation(libs.timber)
}