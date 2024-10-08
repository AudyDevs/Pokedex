plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.daggerHiltAndroid)
}

android {
    namespace = "com.example.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Retrofit 2
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Room
    implementation (libs.androidx.room.ktx)
    kapt (libs.androidx.room.compiler)

    //Datastore preferences
    implementation(libs.androidx.datastore.preferences)

    //Glide
    implementation(libs.glide)

    //Lottie
    implementation(libs.android.lottie)

    //DaggerHilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //c
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)

    //UnitTesting
    testImplementation(libs.junit)
    testImplementation(libs.kotlintest.runner.junit5)
    testImplementation(libs.mockk)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}