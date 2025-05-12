plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
    alias(libs.plugins.cats.onlinebank.di.koin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dev.mokkery)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "com.djvmil.entretienmentor.core.data"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.logging)

            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.call)
            implementation(libs.ktorfit.converters.flow)
            implementation(libs.ktorfit.converters.response)
        }

        commonTest.dependencies {
            //test
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
        }
        appleMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
    tasks.matching { it.name.startsWith("ksp") && it.name.contains("KotlinAndroid") }.configureEach {
        dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
    }

}