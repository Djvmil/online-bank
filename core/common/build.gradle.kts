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

    tasks.matching { it.name.startsWith("ksp") && it.name.contains("IosArm64") }.configureEach {
        dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
    }

}