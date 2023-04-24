plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
    id(Plugins.PARCELIZE)
}

android {
    namespace = "com.wednesday.template.home"
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "home")
}

dependencies {

    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.Core.ui))
    implementation(project(Modules.interactor))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.resources))
    implementation(project(Modules.designSystem))

    implementation(Dependencies.Logging.timber)
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Image.coil)

    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(lifecycleViewModelKtx)
    }
}