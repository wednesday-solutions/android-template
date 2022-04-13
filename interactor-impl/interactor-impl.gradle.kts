plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":interactor"))
    implementation(project(":presentation-entity"))
    implementation(project(":domain"))
    implementation(project(":domain-entity"))
    implementation(project(":resources"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.coroutinesCore)

    implementation(Dependencies.androidCoreKtx)

    implementation(Dependencies.loggingTimber)

    testImplementation(Dependencies.testJunit)
    testImplementation(Dependencies.testKotlinTest)
    testImplementation(Dependencies.testMockito)
    testImplementation(Dependencies.testFlowTest)
    testImplementation(Dependencies.testCoroutines)
    testImplementation(Dependencies.testAndroidxArchCore)
    testImplementation(Dependencies.testAndroidxTestRunner)
    testImplementation(Dependencies.testAndroidxTestRules)
    testImplementation(Dependencies.testAndroidxExt)
    testImplementation(Dependencies.testAndroidxCoreTesting)
}