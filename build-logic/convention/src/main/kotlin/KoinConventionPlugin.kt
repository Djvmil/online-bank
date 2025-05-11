import org.cats.onlinebank.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class KoinConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                sourceSets.named("commonMain").configure {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                }
            }

            dependencies {
                "commonMainImplementation"(libs.findLibrary("koin.core").get())
                "commonMainApi"(platform(libs.findLibrary("koin.annotations.bom").get()))
                "commonMainApi"(libs.findLibrary("koin.annotations").get())

                "commonTestImplementation"(libs.findLibrary("koin.test").get())

                "kspCommonMainMetadata"(libs.findLibrary("koin.ksp.compiler").get())
            }

            project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
                if(name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}
