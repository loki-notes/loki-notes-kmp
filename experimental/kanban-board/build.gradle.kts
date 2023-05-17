import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.shared)

                implementation(compose.desktop.currentOs)
                implementation("io.github.aakira:napier:2.6.1")

            }
        }

        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "Main.kt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kanban-board"
            packageVersion = "1.0.0"
        }
    }
}
