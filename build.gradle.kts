buildscript {
    repositories {
        google()
        mavenCentral()
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("org.jetbrains.compose") version "1.4.0" apply false
    alias(libs.plugins.kotlinAndroid) apply false
}
