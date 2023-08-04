@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
//    id ("com.google.gms.google-services")
    id ("com.google.devtools.ksp") version "1.7.20-1.0.8"
}

android {
    namespace = "cd.wayupdotdev.uza"
    compileSdk = 33

    defaultConfig {
        applicationId = "cd.wayupdotdev.uza"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.androidx.core.splashscreen)

    implementation ("androidx.compose.material3:material3:1.0.1")

    implementation ("androidx.navigation:navigation-compose:2.7.0-rc01")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("androidx.room:room-runtime:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")

    implementation ("io.github.raamcosta.compose-destinations:core:1.7.27-beta")
    implementation ("io.github.raamcosta.compose-destinations:animations-core:1.7.27-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.7.27-beta")

    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

//    implementation ("com.google.firebase:firebase-bom:29.0.4")
//    implementation ("com.google.firebase:firebase-firestore-ktx")
//    implementation ("com.google.firebase:firebase-auth-ktx")
//    implementation ("com.google.firebase:firebase-storage-ktx:20.1.0")
//    implementation ("com.google.android.gms:play-services-auth:20.4.1")

    implementation ("com.google.accompanist:accompanist-permissions:0.28.0")
    implementation ("com.google.accompanist:accompanist-insets:0.28.0")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.28.0")

//    implementation 'androidx.preference:preference-ktx:1.2.0'

//    implementation "com.github.skydoves:landscapist-glide:1.5.0"

    implementation ("com.airbnb.android:lottie-compose:6.0.1")

    implementation ("androidx.camera:camera-core:1.2.1")
    implementation ("androidx.camera:camera-camera2:1.2.1")
    implementation ("androidx.camera:camera-lifecycle:1.2.1")
    implementation ("androidx.camera:camera-view:1.0.0-alpha29")
}