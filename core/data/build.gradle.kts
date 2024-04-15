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
    implementation(libs.retrofit.kotlin.serialization)
}