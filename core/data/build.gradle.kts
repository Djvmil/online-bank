plugins {
    alias(libs.plugins.cats.onlinebank.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.djvmil.entretienmentor.core.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.logging)
    implementation(libs.koin.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.collections.immutable)

    //test
    testImplementation(libs.mockk)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlinx.coroutines.test)
}