import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "org.cats.onlinebank.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
        register("androidApplication") {
            id = "cats.onlinebank.app"
            implementationClass = "AppConventionPlugin"
        }
        register("androidAppCompose") {
            id = "cats.onlinebank.app.compose"
            implementationClass = "AppComposeConventionPlugin"
        }
        register("androidAppKmp") {
            id = "cats.onlinebank.app.kmp"
            implementationClass = "AppKmpConventionPlugin"
        }
        register("androidLibrary") {
            id = "cats.onlinebank.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "cats.onlinebank.library.compose"
            implementationClass = "LibraryComposeConventionPlugin"
        }
        register("androidLibraryKmp") {
            id = "cats.onlinebank.library.kmp"
            implementationClass = "LibraryKmpConventionPlugin"
        }
        register("androidFlavors") {
            id = "cats.onlinebank.app.flavors"
            implementationClass = "AppFlavorsConventionPlugin"
        }
    }
}