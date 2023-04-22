plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":ui:interactor"))
    implementation(project(":ui:presentation-entity"))
    implementation(project(":data:domain"))
    implementation(project(":data:core"))
    implementation(project(":data:domain-entity"))
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