plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Plugins.ANDROID)
}

android {
    namespace = "com.wednesday.template.data.core"
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    
}