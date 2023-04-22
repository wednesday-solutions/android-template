plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(":data:repo"))
    implementation(project(":data:repo-impl"))
    implementation(project(":data:service"))

    implementation(Dependencies.Koin.core)
}