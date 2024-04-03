import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply("sopt.android.library")
                apply("sopt.android.hilt")
                apply("sopt.android.library.compose")
                apply("kotlin-parcelize")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:model"))
                "implementation"(project(":core:designsystem"))

                "implementation"(libs.findBundle("orbit").get())

                "implementation"(libs.findLibrary("kotlinx.coroutines.android").get())
                "implementation"(libs.findLibrary("kotlinx.coroutines.core").get())

                "implementation"(libs.findLibrary("timber").get())
            }
        }
    }
}