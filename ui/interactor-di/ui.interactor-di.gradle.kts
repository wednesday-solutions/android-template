plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(":ui:interactor"))
    implementation(project(":ui:interactor-impl"))
    implementation(project(":data:domain"))

    implementation(Dependencies.Koin.core)
}