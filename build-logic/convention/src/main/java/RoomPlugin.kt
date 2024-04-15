import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.sopt.convention.libs

class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                "implementation"(libs.findLibrary("room.runtime").get())
                "ksp"(libs.findLibrary("room.compiler").get())
                "implementation"(libs.findLibrary("room.ktx").get())
            }
        }
    }
}