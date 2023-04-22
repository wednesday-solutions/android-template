import plugins.PluginConstants

plugins {
    id("com.android.application")
    kotlin("android")
    id(Plugins.KSP)
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
}

android {

//    compileSdk = Versions.SDK.compile
//    buildToolsVersion = Versions.SDK.buildTools
//
//    defaultConfig {
//        minSdk = Versions.SDK.min
//        targetSdk = Versions.SDK.target
//        applicationId = "com.wednesday.template"
//        versionCode = Versions.SDK.versionCode
//        versionName = Versions.SDK.versionName
//        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
//    }

    productFlavors {
        getByName(PluginConstants.QA) {
            dimension = PluginConstants.FLAVOR
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
        }
        getByName(PluginConstants.PRODUCTION) {
            dimension = PluginConstants.FLAVOR
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
        }
        getByName(PluginConstants.DEV) {
            dimension = PluginConstants.FLAVOR
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "${project.rootDir}/tools/proguard-rules.pro"
            )
        }
        applicationVariants.all {
            val variant = this
            variant.outputs
                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    val flavour = variant.flavorName
                    val builtType = variant.buildType.name
                    val versionName = variant.versionName
                    val vCode = variant.versionCode
                    output.outputFileName =
                        "app-${flavour}-${builtType}-${versionName}(${vCode}).apk".replace(
                            "-${flavour}",
                            ""
                        )
                }
        }
    }
}

dependencies {
//    implementation(project(":presentation"))
//    implementation(project(":navigation"))
//    implementation(project(":presentation-di"))
    implementation(project(":navigation-di"))
    implementation(project(":data:domain-di"))
    implementation(project(":data:repo-di"))
    implementation(project(":data:service-di"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Material.material)

    implementation(Dependencies.Logging.timber)

    implementation(Dependencies.Android.splashScreen)
}