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
    implementation(project(":ui:interactor-impl"))
    implementation(project(":data:domain"))

    implementation(Dependencies.Koin.core)
}