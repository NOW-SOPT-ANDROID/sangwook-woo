plugins {
    alias(libs.plugins.sopt.plugin.data)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "org.sopt.data_test"
}

dependencies {
    implementation(projects.core.domain)
}