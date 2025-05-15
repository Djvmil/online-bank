plugins {
    alias(libs.plugins.cats.onlinebank.app.kmp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.dev.mokkery)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "org.cats.onlinebank"
    defaultConfig {
        applicationId = "org.cats.onlinebank"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = ".release"
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
            merges += "META-INF/INDEX.LIST"
            merges += "META-INF/INDEX.LIST"
        }
    }
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.navigation.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.androidx.startup)
        }

        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.data)
            implementation(projects.core.domain)
            implementation(projects.feature)

            implementation(compose.material3)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
        }
    }
}
dependencies {
    debugImplementation(compose.uiTooling)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "org.cats.onlinebank.composeApp.resources"
    generateResClass = always
}