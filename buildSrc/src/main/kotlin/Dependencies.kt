object Dependencies {

    // Compose
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterialIconCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val composeMaterialIconExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinWorkManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // Material
    const val material = "com.google.android.material:material:${Versions.material}"

    // AndroidX
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val androidFragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val androidRecyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val androidLifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val androidRecyclerViewSelection =
        "androidx.recyclerview:recyclerview-selection:${Versions.recyclerViewSelection}"
    const val androidLifecycleLiveDataCore =
        "androidx.lifecycle:lifecycle-livedata-core:${Versions.lifecycle}"
    const val androidLifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val androidLifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val androidLifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val androidLifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val androidNavigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val androidNavigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val androidSplashScreen = "androidx.core:core-splashscreen:${Versions.splash}"


    // Retrofit
    const val retrofitCore = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinxSerializationConverter}"
    const val retrofitLogging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}"

    // Logging
    const val loggingTimber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val loggingChucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"


    // Test
    const val testAndroidxTestCore = "androidx.test:runner:1.3.0"
    const val testAndroidxArchCore = "androidx.arch.core:core-testing:2.1.0"
    const val testAndroidxTestRunner = "androidx.test:runner:1.3.0"
    const val testAndroidxTestRules = "androidx.test:rules:1.3.0"
    const val testAndroidxExt = "androidx.test.ext:junit:1.1.2"
    const val testAndroidxCoreTesting = "androidx.arch.core:core-testing:2.1.0"
    const val testAndroidxRoomTesting = "androidx.room:room-testing:2.2.5"
    const val testJunit = "junit:junit:4.13.2"
    const val testCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val testFlowTest = "app.cash.turbine:turbine:0.6.0"
    const val testKotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val testMockito = "org.mockito.kotlin:mockito-kotlin:3.2.0"
}
