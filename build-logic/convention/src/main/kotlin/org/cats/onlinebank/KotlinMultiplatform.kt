/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cats.onlinebank

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * A plugin that applies the Kotlin Multiplatform plugin and configures it for the project.
 * https://github.com/cashapp/sqldelight/blob/master/buildLogic/multiplatform-convention/src/main/kotlin/app/cash/sqldelight/multiplatform/MultiplatformConventions.kt
 */
internal fun Project.configureKotlinMultiplatform() {
    extensions.configure<KotlinMultiplatformExtension> {
        // Enable native group by default
        // https://kotlinlang.org/docs/whatsnew1820.html#new-approach-to-source-set-hierarchy
        applyDefaultHierarchyTemplate()

        iosSimulatorArm64()
        iosX64()
        iosArm64()

        // Suppress 'expect'/'actual' classes are in Beta.
        targets.configureEach {
            compilations.configureEach {
                compileTaskProvider.configure {
                    compilerOptions {
                        freeCompilerArgs.addAll("-Xexpect-actual-classes")
                    }
                }
            }
        }

        // Fixes Cannot locate tasks that match ':core:model:testClasses' as task 'testClasses'
        // not found in project ':core:model'. Some candidates are: 'jsTestClasses', 'jvmTestClasses'.
        project.tasks.create("testClasses") {
            dependsOn("allTests")
        }
    }
}
