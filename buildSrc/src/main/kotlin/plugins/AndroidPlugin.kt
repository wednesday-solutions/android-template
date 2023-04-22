package plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File
import java.io.FileInputStream
import java.util.Properties

open class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
        }

        project.apply {
            from("${project.rootProject.projectDir}/lint.gradle")
        }

        project.applyBuildTypes()

        project.androidExtension?.apply {
            buildToolsVersion = Versions.SDK.buildTools

            compileSdkVersion(Versions.SDK.compile)
            defaultConfig {
                targetSdk = Versions.SDK.target
                minSdk = Versions.SDK.min
                versionCode = Versions.SDK.versionCode
                versionName = Versions.SDK.versionName
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

//            val props = Properties()
//            props.load(FileInputStream(File("local.properties")))

            productFlavors {
                getByName(PluginConstants.PRODUCTION) {
//                    buildConfigField(
//                        "String", "OPEN_WEATHER_API_KEY",
//                        props["OPEN_WEATHER_API_KEY"] as String
//                    )
                }
                getByName(PluginConstants.QA) {
//                    buildConfigField(
//                        "String", "OPEN_WEATHER_API_KEY",
//                        props["OPEN_WEATHER_API_KEY"] as String
//                    )
                }
                getByName(PluginConstants.DEV) {
//                    buildConfigField(
//                        "String", "OPEN_WEATHER_API_KEY",
//                        props["OPEN_WEATHER_API_KEY"] as String
//                    )
                }
            }
        }

        project.dependencies {
            implementation(Dependencies.Logging.timber)
        }
    }

    private fun Project.applyBuildTypes() {
        val androidExtension =
            project.extensions.getByName(PluginConstants.ANDROID) as BaseExtension

        androidExtension.apply {
            buildTypes {
                getByName(PluginConstants.RELEASE) {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                getByName(PluginConstants.DEBUG) {
                    debuggable(true)
                    getIsDefault().set(true)
                    isMinifyEnabled = false
                }
            }

            flavorDimensions(PluginConstants.FLAVOR)
            productFlavors {
                create(PluginConstants.PRODUCTION) {
                    dimension = PluginConstants.FLAVOR
                }
                create(PluginConstants.QA) {
                    getIsDefault().set(true)
                    dimension = PluginConstants.FLAVOR
                }
                create(PluginConstants.DEV) {
                    dimension = PluginConstants.FLAVOR
                }
            }
        }
    }

}
