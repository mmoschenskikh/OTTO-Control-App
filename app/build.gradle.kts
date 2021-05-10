plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kapt)
}

android {
    compileSdkVersion(AppConfig.Sdk.compile)

    defaultConfig {
        applicationId = AppConfig.appId
        minSdkVersion(AppConfig.Sdk.min)
        targetSdkVersion(AppConfig.Sdk.target)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testRunner
    }
    buildFeatures.viewBinding = true
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.kotlin)

    implementation(Android.activityKtx)
    implementation(Android.appcompat)
    implementation(Android.constraintLayout)
    implementation(Android.coreKtx)
    implementation(Android.fragmentKtx)
    implementation(Android.gridLayout)

    implementation(Android.localBroadcastManager)

    implementation(Navigation.navigationUiKtx)
    implementation(Navigation.navigationFragmentKtx)

    implementation(Dependencies.colorPicker)
    implementation(Dependencies.materialDesign)

    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)

    implementation(Android.gridLayout)
}
