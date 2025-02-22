plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.videoviwer.video"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core)

    implementation(libs.gson)

    // dependency for exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    // for core support in exoplayer.
    implementation("com.google.android.exoplayer:exoplayer-core:2.19.1")
    // for adding dash support in our exoplayer.
    implementation("com.google.android.exoplayer:exoplayer-dash:2.19.1")
    // for adding hls support in exoplayer.
    implementation("com.google.android.exoplayer:exoplayer-hls:2.19.1")
    // for smooth streaming of video in our exoplayer.
    implementation("com.google.android.exoplayer:exoplayer-smoothstreaming:2.19.1")
    // for generating default ui of exoplayer
    implementation("com.google.android.exoplayer:exoplayer-ui:2.19.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}