@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
//    id ("com.google.gms.google-services")
    id ("com.google.devtools.ksp") version "1.8.20-1.0.10"
}

android {
    namespace = "cd.wayupdotdev.uza"
    compileSdk = 34

    defaultConfig {
        applicationId = "cd.wayupdotdev.uza"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
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

    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.hilt.navigation.compose)

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    implementation (libs.core)
    implementation (libs.animations.core)
    ksp (libs.ksp)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.play.services)

    implementation (libs.accompanist.permissions)
    implementation (libs.accompanist.insets)
    implementation (libs.accompanist.swiperefresh)

    implementation (libs.lottie.compose)

    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.view)


    implementation (libs.androidx.datastore.preferences)

//    implementation ("com.google.firebase:firebase-bom:29.0.4")
//    implementation ("com.google.firebase:firebase-firestore-ktx")
//    implementation ("com.google.firebase:firebase-auth-ktx")
//    implementation ("com.google.firebase:firebase-storage-ktx:20.1.0")
//    implementation ("com.google.android.gms:play-services-auth:20.4.1")

//    implementation 'androidx.preference:preference-ktx:1.2.0'

//    implementation "com.github.skydoves:landscapist-glide:1.5.0"
}