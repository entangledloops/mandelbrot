rootProject.name = "mandelbrot"

include("core", "desktop")

val kotlinVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "kotlin" -> useVersion(kotlinVersion)
            }
        }
    }
}