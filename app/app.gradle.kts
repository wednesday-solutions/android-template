plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
}

android {
    defaultConfig {
        applicationId = "com.wednesday.template"
        versionCode = 5
        versionName = "1.0"
    }
    flavorDimensions("version")
    productFlavors {
        create("qa") {
            dimension("version")
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
        }
        create("prod") {
            dimension("version")
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
        }
        create("dev") {
            dimension("version")
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
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
    implementation(project(":presentation"))
//    implementation(project(":navigation"))
    implementation(project(":presentation-di"))
    implementation(project(":navigation-di"))
    implementation(project(":interactor-di"))
    implementation(project(":domain-di"))
    implementation(project(":repo-di"))
    implementation(project(":service-di"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)

    implementation(Dependencies.material)

    implementation(Dependencies.loggingTimber)

    implementation(Dependencies.androidSplashScreen)
}