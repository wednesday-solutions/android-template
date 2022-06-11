plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Android.coreKtx)

    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.material)
}