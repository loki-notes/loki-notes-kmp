plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.touchlab.kermit)
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
            }
        }
        val nativeMain by creating
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
        val linuxMain by creating
        val macosMain by creating
        val windowsMain by creating
        val iosX64Main by getting 
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val linuxX64Main by getting
        val macosX64Main by getting 
        val macosArm64Main by getting
        val mingwX64Main by getting

        /* Main hierarchy */
        nativeMain.dependsOn(commonMain)
        androidMain.dependsOn(commonMain)
        iosMain.dependsOn(nativeMain)
        iosX64Main.dependsOn(iosMain)
        iosArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Main.dependsOn(iosMain)
        linuxMain.dependsOn(nativeMain)
        linuxX64Main.dependsOn(linuxMain)
        macosMain.dependsOn(nativeMain)
        macosX64Main.dependsOn(macosMain)
        macosArm64Main.dependsOn(macosMain)
        windowsMain.dependsOn(nativeMain)
        mingwX64Main.dependsOn(windowsMain)

        /* Test source sets */
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val nativeTest by creating
        val androidUnitTest by getting
        val iosTest by creating
        val linuxTest by creating
        val macosTest by creating
        val windowsTest by creating
        val iosX64Test by getting 
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val linuxX64Test by getting
        val macosX64Test by getting 
        val macosArm64Test by getting
        val mingwX64Test by getting

        /* Test hierarchy */
        nativeTest.dependsOn(commonTest)
        androidUnitTest.dependsOn(commonTest)
        iosTest.dependsOn(nativeTest)
        iosX64Test.dependsOn(iosTest)
        iosArm64Test.dependsOn(iosTest)
        iosSimulatorArm64Test.dependsOn(iosTest)
        linuxTest.dependsOn(nativeTest)
        linuxX64Test.dependsOn(linuxTest)
        macosTest.dependsOn(nativeTest)
        macosX64Test.dependsOn(macosTest)
        macosArm64Test.dependsOn(macosTest)
        windowsTest.dependsOn(nativeTest)
        mingwX64Test.dependsOn(windowsTest)
    }
}

android {
    namespace = "org.lokinotes"
    compileSdk = 31
    defaultConfig {
        minSdk = 21
    }
}
