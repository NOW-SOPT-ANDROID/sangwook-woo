@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.plugin.feature)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.home"
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
}