plugins {
    alias(libs.plugins.sopt.plugin.data)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "org.sopt.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.datastore)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
}