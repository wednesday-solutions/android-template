plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":ui:presentation-entity"))
    implementation(project(":navigation"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.navigationFragment)

    testImplementation(Dependencies.Test.androidxTestCore)
    testImplementation(Dependencies.Test.androidxExt)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
}