plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
    alias(libs.plugins.cats.onlinebank.di.koin)
}

android {
    namespace = "org.cats.onlinebank.core.common"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
            api(libs.kotlinx.serialization.json)
            api(libs.kotlinx.coroutines.test)
            api(libs.kotlin.test)
        }
    }

    // KSP
    tasks.matching {
        it.name.contains("kspKotlinIosArm64") ||
                (it.name.startsWith("ksp") && it.name.contains("KotlinAndroid") )}.configureEach {
        dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
    }
}