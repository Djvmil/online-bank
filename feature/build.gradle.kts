plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.cats.onlinebank.di.koin)
    alias(libs.plugins.dev.mokkery)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "org.cacts.onlinebank.feature"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
    testOptions {
        animationsDisabled = true
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.domain)
            implementation(projects.core.data)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
    }
}

tasks.matching { it.name.startsWith("ksp") && it.name.contains("KotlinAndroid") }.configureEach {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

}