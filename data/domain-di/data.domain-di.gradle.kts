plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}


dependencies {
    implementation(project(":data:domain"))
    implementation(project(":data:repo"))
    implementation(project(":data:domain-impl"))

    implementation(Dependencies.Koin.core)
}