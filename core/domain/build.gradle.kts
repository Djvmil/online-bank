plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
    alias(libs.plugins.cats.onlinebank.di.koin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dev.mokkery)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "org.cats.onlinebank.core.data"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.data)
        }
    }

    // KSP
    tasks.matching {
        it.name.contains("kspKotlinIosArm64") ||
                (it.name.startsWith("ksp") && it.name.contains("KotlinAndroid") )}.configureEach {
        dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
    }

}