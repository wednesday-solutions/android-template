plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.serialization)
    kapt(Dependencies.Room.compiler)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
}