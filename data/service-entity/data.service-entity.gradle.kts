plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.KSP)
    id(Plugins.KOTLIN_SERIALIZATION)
}

dependencies {

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.serialization)
    ksp(Dependencies.Room.compiler)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
}