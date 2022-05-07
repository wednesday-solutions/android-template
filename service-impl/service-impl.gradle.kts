plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

//android {
//    compileOptions {
//        isCoreLibraryDesugaringEnabled = true
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//
//}

dependencies {

    implementation(project(":service-entity"))
    implementation(project(":service"))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinSerialization)

    implementation(Dependencies.coroutinesCore)

    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)

    implementation(Dependencies.retrofitCore)
    implementation(Dependencies.retrofitSerialization)
    implementation(Dependencies.retrofitLogging)

    implementation(Dependencies.loggingTimber)
    implementation(Dependencies.loggingChucker)

    androidTestImplementation(Dependencies.testAndroidxTestCore)
    androidTestImplementation(Dependencies.testAndroidxTestRunner)
    androidTestImplementation(Dependencies.testAndroidxTestRules)
    androidTestImplementation(Dependencies.testAndroidxExt)
    androidTestImplementation(Dependencies.testKotlinTest)
    androidTestImplementation(Dependencies.testFlowTest)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}