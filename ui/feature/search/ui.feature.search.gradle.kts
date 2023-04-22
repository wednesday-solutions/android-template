plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
    id(Plugins.PARCELIZE)
}

android {
    namespace = "com.wednesday.template.search"
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "search")
}

dependencies {
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.Core.ui))
    implementation(project(Modules.interactor))
//    implementation(project(Modules.domain))
    implementation(project(Modules.Core.data))
//    implementation(project(Modules.domainEntity))
    implementation(project(Modules.resources))

    implementation(Dependencies.Logging.timber)
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(lifecycleViewModelKtx)
    }

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

}