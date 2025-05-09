plugins {
    alias(libs.plugins.cats.onlinebank.library.kmp)
}

android {
    namespace = "org.cats.onlinebank.core.common"
}

kotlin {
    androidTarget()
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
            api(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            api(libs.kotlinx.coroutines.test)
            api(libs.kotlin.test)
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}