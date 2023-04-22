plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "compose"
            implementationClass = "plugins.ComposePlugin"
        }

        register("android") {
            id = "android"
            implementationClass = "plugins.AndroidPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
    implementation(kotlin("gradle-plugin", "1.7.0"))
}