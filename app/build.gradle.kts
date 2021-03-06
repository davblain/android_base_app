val toothpickVersion = "3.1.0"
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}
apply(from = "${project.rootDir}/codequality/ktlint.gradle.kts")
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.gemini.base"
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
    implementation(project(":mvi"))
    implementation(project(":paging-feature"))
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.0")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:4.3.0")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion")
}