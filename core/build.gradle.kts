
import org.jetbrains.kotlin.config.KotlinCompilerVersion

val mviCoreVersion = "1.2.4"
val toothpickVersion = "3.1.0"
val threetenABPVersion = "1.1.0"
val stethoVersion = "1.5.1"
val timberVersion = "4.7.0"
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}
apply(from = "${project.rootDir}/codequality/ktlint.gradle.kts")
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
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //Android Specific
    api("androidx.appcompat:appcompat:1.1.0")
    api("com.google.android.material:material:1.0.0")
    api("androidx.recyclerview:recyclerview:1.0.0")
    api("androidx.constraintlayout:constraintlayout:1.1.3")
    api("com.jakewharton.timber:timber:$timberVersion")
    api("com.facebook.stetho:stetho:$stethoVersion")
    api("com.jakewharton.threetenabp:threetenabp:$threetenABPVersion")
    //core.di.DI
    api("com.github.stephanenicolas.toothpick:ktp:$toothpickVersion")
    //Navigation
    api("com.bluelinelabs:conductor:3.0.0-rc1")
    api("com.bluelinelabs:conductor-archlifecycle:3.0.0-rc1")
    //implementation("com.bluelinelabs:conductor-support:3.0.0-rc1")
    //Image load and cache
    api("io.coil-kt:coil:0.8.0")
    //Tests
    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}