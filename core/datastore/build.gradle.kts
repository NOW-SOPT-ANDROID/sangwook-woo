@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sopt.plugin.test)
}

android {
    namespace = "org.sopt.datastore"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.datastore)
}
