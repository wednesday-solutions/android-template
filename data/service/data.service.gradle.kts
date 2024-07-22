plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.serviceEntity))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.serialization)
    implementation(Dependencies.Retrofit.logging)
}