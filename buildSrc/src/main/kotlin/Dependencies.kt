object Dependencies {

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialIconCore =
            "androidx.compose.material:material-icons-core:${Versions.compose}"
        const val materialIconExtended =
            "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val animation = "androidx.compose.animation:animation:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val workManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Google {
        const val services = "com.google.gms:google-services:${Versions.googleServices}"
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
        const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val lifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val recyclerViewSelection =
            "androidx.recyclerview:recyclerview-selection:${Versions.recyclerViewSelection}"
        const val lifecycleLiveDataCore =
            "androidx.lifecycle:lifecycle-livedata-core:${Versions.lifecycle}"
        const val lifecycleLiveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
        const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splash}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val serialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinxSerializationConverter}"
        const val logging =
            "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    }

    object Test {
        const val androidxTestCore = "androidx.test:runner:1.3.0"
        const val androidxArchCore = "androidx.arch.core:core-testing:2.1.0"
        const val androidxTestRunner = "androidx.test:runner:1.3.0"
        const val androidxTestRules = "androidx.test:rules:1.3.0"
        const val androidxExt = "androidx.test.ext:junit:1.1.2"
        const val androidxCoreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val androidxRoomTesting = "androidx.room:room-testing:2.2.5"
        const val junit = "junit:junit:4.13.2"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val flowTest = "app.cash.turbine:turbine:0.6.0"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val mockito = "org.mockito.kotlin:mockito-kotlin:3.2.0"
    }
}
