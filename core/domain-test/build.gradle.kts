plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.sopt.android.test)
}

android {
    namespace = "org.sopt.domain_test"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.dataTest)
    implementation(projects.core.model)
}