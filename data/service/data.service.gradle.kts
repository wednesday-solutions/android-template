plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":data:service-entity"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.serialization)
    implementation(Dependencies.Retrofit.logging)
}