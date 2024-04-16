@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.sopt.plugin.feature)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.mypage"
}

dependencies {
    implementation(projects.core.domain)
}