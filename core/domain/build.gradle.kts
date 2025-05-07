plugins {
    alias(libs.plugins.cats.onlinebank.library)
}

android {
    namespace = "com.djvmil.entretienmentor.core.domain"
}
dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)

    implementation(libs.koin.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}