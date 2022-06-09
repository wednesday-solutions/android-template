plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

android {
    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    implementation(project(":presentation-entity"))
    implementation(project(":interactor"))
    implementation(project(":resources"))
    implementation(project(":navigation"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.navigation)
    implementation(Dependencies.Koin.compose)

    implementation(Dependencies.Material.material)

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.viewModel)
    implementation(Dependencies.Compose.materialIconCore)
    implementation(Dependencies.Compose.materialIconExtended)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.liveData)

    implementation(Dependencies.Image.coil)

    implementation(Dependencies.Logging.timber)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.fragment)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.Android.lifecycleLiveDataCore)
    implementation(Dependencies.Android.lifecycleLiveDataKtx)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.lifecycleViewModelKtx)
    implementation(Dependencies.Android.navigationFragment)
    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.splashScreen)

    testImplementation(Dependencies.Test.androidxArchCore)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.flowTest)
    testImplementation(Dependencies.Test.mockito)

    androidTestImplementation(Dependencies.Compose.uiTest)
}