plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sopt.plugin.test)
}

android {
    namespace = "org.sopt.network"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(libs.bundles.coroutine)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}