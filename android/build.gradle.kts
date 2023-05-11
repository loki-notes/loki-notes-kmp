plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "org.lokinotes"
    compileSdk = 33

    defaultConfig {
        applicationId = "org.lokinotes"

        minSdk = 26

        versionCode = 1
        versionName = "0.0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.androidx.ui)
}