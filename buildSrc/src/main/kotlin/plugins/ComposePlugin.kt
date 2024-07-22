package plugins

import Dependencies
import Versions
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.plugins
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class ComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.plugins.apply {
            apply(Plugins.KSP)
        }

        project.androidExtension?.apply {
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.composeCompiler
            }
            buildFeatures.apply {
                compose = true
            }
        }

        project.tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                )
            }
        }

        project.dependencies {
            with(Dependencies.Compose) {
                implementation(platform(composeBOM))
                implementation(activity)
                implementation(material)
                implementation(foundation)
                implementation(runtime)
                implementation(animation)
                implementation(uiTooling)
                implementation(viewModel)
                implementation(uiTest)
                implementation(uiToolingPreview)
                implementation(composeDestinations)
                implementation(lifecycleRuntime)
                ksp(composeDestinationsKsp)
            }

            with(Dependencies.Kotlin) {
                implementation(immutableCollection)
            }

            with(Dependencies.Koin) {
                implementation(compose)
            }
        }
    }
}