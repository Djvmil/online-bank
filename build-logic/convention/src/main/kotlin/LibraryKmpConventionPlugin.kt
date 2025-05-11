import com.android.build.gradle.LibraryExtension
import org.cats.onlinebank.configureKotlinAndroid
import org.cats.onlinebank.configureKotlinMultiplatform
import org.cats.onlinebank.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class LibraryKmpConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply(KoinConventionPlugin::class.java)
            }
            configureKotlinMultiplatform()
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix = path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_").lowercase() + "_"
            }

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                sourceSets.named("commonMain").configure {
                     kotlin.srcDir( "build/generated/ksp/commonMain/kotlin" )

                }
            }
            dependencies {
                "commonTestImplementation"(libs.findLibrary("kotlin.test").get())
                "commonTestImplementation"(libs.findLibrary("turbine").get())
                "commonTestImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())
            }
        }
    }
}