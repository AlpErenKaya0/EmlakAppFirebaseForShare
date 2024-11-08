plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.emlakappfirebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.emlakappfirebase"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation(libs.koin.android)
    implementation (libs.koin.androidx.compose)
    implementation(libs.insert.koin.koin.androidx.compose)

    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)

    implementation (libs.androidx.core.ktx)

    implementation (libs.androidx.appcompat)
    implementation (libs.material)
    implementation (libs.androidx.constraintlayout)

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.espresso.core.v350)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.com.squareup.okhttp3.okhttp3)

    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    implementation (libs.coil.compose)
    implementation (libs.androidx.material)
    implementation (libs.androidx.material.icons.extended)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage.z)
    implementation(libs.firebase.crashlytics)

    implementation(libs.androidx.multidex)

    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation (libs.coil.compose.v210)

        // For developers using AndroidX in their applications
    implementation (libs.easypermissions.v300)

        // For developers using the Android Support Library
    implementation (libs.devrel.easypermissions)

    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation (libs.androidx.room.paging)
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.30.0")
}    /*
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)
*/


//ilerisi için debug ve release ayrılabilir.