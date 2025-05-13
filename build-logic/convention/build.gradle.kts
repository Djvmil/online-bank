import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "org.cats.onlinebank.buildlogic"
java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidAppKmp") {
            id = "cats.onlinebank.app.kmp"
            implementationClass = "AppKmpConventionPlugin"
        }
        register("androidLibraryKmp") {
            id = "cats.onlinebank.library.kmp"
            implementationClass = "LibraryKmpConventionPlugin"
        }
        register("koin") {
            id = "cats.onlinebank.di.koin"
            implementationClass = "KoinConventionPlugin"
        }
    }
}