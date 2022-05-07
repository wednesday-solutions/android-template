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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinNavigation)
    implementation(Dependencies.koinCompose)

    implementation(Dependencies.material)

    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeAnimation)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.viewModelCompose)
    implementation(Dependencies.composeMaterialIconCore)
    implementation(Dependencies.composeMaterialIconExtended)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeLiveData)

    implementation(Dependencies.loggingTimber)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidFragment)
    implementation(Dependencies.androidConstraintLayout)
    implementation(Dependencies.androidRecyclerView)
    implementation(Dependencies.androidLifecycleLiveDataCore)
    implementation(Dependencies.androidLifecycleLiveDataKtx)
    implementation(Dependencies.androidLifecycleViewModel)
    implementation(Dependencies.androidLifecycleViewModelKtx)
    implementation(Dependencies.androidNavigationFragment)
    implementation(Dependencies.androidNavigationUi)
    implementation(Dependencies.androidSplashScreen)

    testImplementation(Dependencies.testAndroidxArchCore)
    testImplementation(Dependencies.testCoroutines)
    testImplementation(Dependencies.testKotlinTest)
    testImplementation(Dependencies.testFlowTest)
    testImplementation(Dependencies.testMockito)

    androidTestImplementation(Dependencies.composeUiTest)
}