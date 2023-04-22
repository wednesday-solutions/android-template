plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(":data:service"))
    implementation(project(":data:service-impl"))

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Retrofit.core)
}