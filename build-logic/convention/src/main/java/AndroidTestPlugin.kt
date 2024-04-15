import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.sopt.convention.libs

class AndroidTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                "androidTestImplementation"(libs.findLibrary("androidx.test.runner").get())
                "debugImplementation"(libs.findLibrary("androidx.test.core").get())
                "androidTestImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())
                "implementation"(libs.findLibrary("androidx.test.ext.junit").get())
            }
        }
    }
}