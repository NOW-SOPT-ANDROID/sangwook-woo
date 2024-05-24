import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.sopt.convention.libs

internal class DataPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("sopt.android.library")
                apply("sopt.android.hilt")
            }

            dependencies {
                "implementation"(project(":core:model"))
                "implementation"(libs.findBundle("coroutine").get())
                "implementation"(libs.findLibrary("androidx-paging").get())
            }
        }
    }
}