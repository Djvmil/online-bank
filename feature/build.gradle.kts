plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.serialization)
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
    androidTarget()
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.domain)
            implementation(projects.core.data)
            implementation(compose.material3)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

/*  implementation(libs.androidx.navigation.compose)
implementation(libs.koin.compose.navigation)
implementation(libs.androidx.material3)
implementation(libs.kotlinx.datetime)

implementation(projects.core.ctation(projects.core.domain)
implementation(projects.core.data)
implementation(libs.androidx.navigation.compose)
implementation(libs.koin.compose.navigation)
implementation(libs.androidx.material3)
implementation(libs.kotlinx.datetime)

testImplementation(libs.mockk)
testImplementation(libs.turbine)
testImplementation(libs.kotlinx.coroutines.test)
debugImplementation(libs.androidx.ui.test.manifest)
androidTestImplementation(libs.androidx.test.runner)
androidTestImplementation(libs.androidx.test.rules)
androidTestImplementation(libs.androidx.espresso.core)
androidTestImplementation(libs.androidx.ui.test.junit4) */
}
}

tasks.matching { it.name.startsWith("ksp") && it.name.contains("KotlinAndroid") }.configureEach {
dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

}