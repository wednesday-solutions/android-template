plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

android {
    namespace = "com.wednesday.template.search"
}

dependencies {
    implementation(project(":ui:presentation-entity"))
    implementation(project(":ui:core"))
    implementation(project(":ui:interactor"))
    implementation(project(":data:domain"))
    implementation(project(":data:core"))
    implementation(project(":data:domain-entity"))
    implementation(project(":resources"))

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
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}