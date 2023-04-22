plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(":data:domain-entity"))
    implementation(project(":data:domain"))
    implementation(project(":data:core"))
    implementation(project(":ui:presentation-entity"))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)
}