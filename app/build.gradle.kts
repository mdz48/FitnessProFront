plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.secrets.gradle)
}

android {
    namespace = "com.example.fitnessprofront"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.fitnessprofront"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Cargar propiedades desde local.properties
        val properties = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir, providers)
        buildConfigField("String", "BASE_URL", "\"${properties.getProperty("backend_url", "http://10.0.2.2:8000/api/v1/")}\"")
        buildConfigField("String", "API_HOST", "\"${properties.getProperty("host", "exercisedb.p.rapidapi.com")}\"")
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("key", "")}\"")
        buildConfigField("String", "EXERCISE_DB_URL", "\"${properties.getProperty("url", "https://exercisedb-api.vercel.app/api/v1/")}\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.io.coil.kt.coil.compose)      // Coil
    implementation(libs.io.coil.kt.coil.gif)          // Coil GIF
    implementation(libs.com.squareup.retrofit2.retrofit)        // Retrofit
    implementation(libs.com.squareup.retrofit2.converter.json)  // JSON
    implementation(libs.androidx.ui)
    implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel Compose

}