@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.plugin.feature)
    alias(libs.plugins.kotlin.android)
}
android {
    namespace = "org.sopt.home"

    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.constraintlayout)
}