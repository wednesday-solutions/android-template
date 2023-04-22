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
    implementation(project(Modules.service))
    implementation(project(Modules.serviceImpl))
    implementation(project(Modules.serviceDI))
    implementation(project(Modules.serviceEntity))
    implementation(project(Modules.repo))
    implementation(project(Modules.repoImpl))
    implementation(project(Modules.repoDI))
    implementation(project(Modules.domain))
    implementation(project(Modules.domainImpl))
    implementation(project(Modules.domainDI))
    implementation(project(Modules.domainEntity))


    implementation(project(Modules.home))
    implementation(project(Modules.interactor))
    implementation(project(Modules.interactorDI))
    implementation(project(Modules.interactorImpl))
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.designSystem))
    implementation(project(Modules.search))

    implementation(project(Modules.navigation))
    implementation(project(Modules.navigationDi))
    implementation(project(Modules.navigationImpl))

    implementation(project(Modules.resources))

    implementation(project(Modules.Core.ui))
    implementation(project(Modules.Core.data))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Material.material)

    implementation(Dependencies.Logging.timber)

    implementation(Dependencies.Android.splashScreen)
}