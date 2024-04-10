import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.sopt.convention.Const

class JavaLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = Const.JAVA_VERSION
                targetCompatibility = Const.JAVA_VERSION
            }

            extensions.configure<KotlinProjectExtension> {
                jvmToolchain(Const.JDK_VERSION)
            }
        }
    }
}