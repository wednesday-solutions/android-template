plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":presentation-entity"))
    implementation(project(":resources"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.navigationFragment)
}