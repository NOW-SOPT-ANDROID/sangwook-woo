@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.plugin.feature)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.main"
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.constraintlayout)
    implementation(projects.feature.home)
    implementation(projects.feature.mypage)
    implementation(projects.feature.search)
}
