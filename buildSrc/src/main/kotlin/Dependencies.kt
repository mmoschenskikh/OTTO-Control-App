object AppConfig {
    const val appId = "ru.spbstu.ottocontrol"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Sdk {
        const val min = 21
        const val compile = 30
        const val target = compile
    }
}

object Versions {
    const val kotlin = "1.4.32"
    const val androidGradlePlugin = "4.1.3"

    const val appCompat = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.3.2"
    const val fragmentKtx = "1.3.3"
    const val material = "1.3.0"
    const val navigation = "2.3.5"

    const val jUnit = "4.13.2"
    const val extJUnit = "1.1.1"
    const val espresso = "3.3.0"
}

object BuildPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"
}

object Navigation {
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
