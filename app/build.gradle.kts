import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val apikeyPropertiesFile = rootProject.file("key.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))


android {
    namespace = "com.example.digikala"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digikala"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "X_API_KEY", apikeyProperties["X_API_KEY"].toString())
        buildConfigField("String", "KEY", apikeyProperties["KEY"].toString())
        buildConfigField("String", "IV", apikeyProperties["IV"].toString())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material:material:1.5.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //room
    val roomVersion = "2.6.1"
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    //datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //hilt di
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    //animation
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //coil - load image from url
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    //swipe refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")

    //system ui controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    //Accompanist-Pager
    implementation("com.google.accompanist:accompanist-pager:0.29.0-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.29.0-alpha")

    //zarinpal
    implementation("com.zarinpal:payment-provider-ktx:0.5.3")

    //paging
    implementation("androidx.paging:paging-compose:1.0.0-alpha10")
}