pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // NewPipeExtractor is published via JitPack.
        maven("https://jitpack.io")
    }
}

rootProject.name = "BitChord"
include(":shared")
include(":sharedUi")

// This fork targets the Android phone app only. TV and Wear OS are not part
// of its application build or release pipeline.
// The Android module is included only when an SDK is available; desktop-only
// development environments can still configure the remaining targets.
// Read local.properties as a Properties file because Android Studio escapes
// Windows paths (for example, C\:\Users\...).
val androidSdk: String? = file("local.properties")
    .takeIf { it.isFile }
    ?.let { f -> java.util.Properties().apply { f.inputStream().use(::load) } }
    ?.getProperty("sdk.dir")
    ?.trim()
    ?: System.getenv("ANDROID_HOME")
    ?: System.getenv("ANDROID_SDK_ROOT")

if (androidSdk != null && file(androidSdk).isDirectory) {
    include(":app")
} else {
    logger.lifecycle("No Android SDK found — building the desktop targets only.")
}
