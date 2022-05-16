plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":service"))
    implementation(project(":service-impl"))

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Retrofit.core)
}