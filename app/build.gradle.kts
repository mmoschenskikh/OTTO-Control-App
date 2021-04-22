plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
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
    implementation(Android.coreKtx)
    implementation(Android.appcompat)
    implementation(Dependencies.materialDesign)
    implementation(Android.constraintLayout)
    implementation("com.android.support:support-v13:28.0.0")
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.activity:activity-ktx:1.2.2")

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    implementation("androidx.fragment:fragment-ktx:1.3.2")

    implementation("com.android.support:gridlayout-v7:28.0.0")
    implementation("com.android.support:appcompat-v7:28.0.0")

    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
}