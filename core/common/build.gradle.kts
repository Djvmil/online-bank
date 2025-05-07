plugins {
    alias(libs.plugins.cats.onlinebank.library)
}

android {
    namespace = "org.cats.onlinebank.core.common"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.koin.test.junit4)
}