plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.videoviwer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.videoviwer.application"
        minSdk = 26
        targetSdk = 35
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
    implementation(projects.features.video)
    implementation(projects.designSystem)

    implementation(libs.gson)

    implementation("com.google.dagger:dagger:2.51.1")
    implementation(libs.androidx.junit.ktx)
    kapt("com.google.dagger:dagger-compiler:2.51.1")

    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    testImplementation(libs.junit)
//    testImplementation("org.mockito:mockito-inline:4.0.0")
//    testImplementation("org.mockito:mockito-core:3.12.4")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("io.mockk:mockk:1.13.3")
//    testImplementation("org.robolectric:robolectric:4.9")
    testImplementation("androidx.test.ext:junit:1.2.1")
    // AndroidX Test Extensions and Rules
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test:rules:1.4.0")

    // Coroutines Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    // AndroidX Arch Core Testing
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    implementation("net.bytebuddy:byte-buddy:1.14.12")

}