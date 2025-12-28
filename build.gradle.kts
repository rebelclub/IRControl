plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    kotlin("android") version "1.8.10" apply false
    kotlin("kapt") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// Global dependency versions
ext {
    set("kotlin_version", "1.8.10")
    set("compose_version", "1.4.3")
    set("hilt_version", "2.45")
    set("gradle_version", "7.4.2")
    set("compileSdkVersion", 34)
    set("minSdkVersion", 24)
    set("targetSdkVersion", 34)
}
