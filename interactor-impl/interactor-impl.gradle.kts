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

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Android.coreKtx)

    implementation(Dependencies.Logging.timber)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.flowTest)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.androidxArchCore)
    testImplementation(Dependencies.Test.androidxTestRunner)
    testImplementation(Dependencies.Test.androidxTestRules)
    testImplementation(Dependencies.Test.androidxExt)
    testImplementation(Dependencies.Test.androidxCoreTesting)
}