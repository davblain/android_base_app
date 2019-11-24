val mviVersion = "1.2.4"
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}
dependencies {
    api("com.github.badoo.mvicore:mvicore:$mviVersion")
    api("com.github.badoo.mvicore:mvicore-android:$mviVersion")
    api("io.reactivex.rxjava2:rxjava:2.2.14")
    api ("io.reactivex.rxjava2:rxandroid:2.1.1")
}