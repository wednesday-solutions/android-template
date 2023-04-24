plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.service))
    implementation(project(Modules.serviceImpl))

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Retrofit.core)
}