plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
    id(Plugins.PARCELIZE)
}

android {
    namespace = "com.wednesday.template.feature.core"
}

dependencies {
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.domainEntity))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.resources))
    implementation(project(Modules.designSystem))

    implementation(Dependencies.Koin.core)

    implementation(Dependencies.Logging.timber)

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    
}