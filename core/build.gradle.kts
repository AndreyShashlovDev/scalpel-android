plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.trading.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "APP_NAME",
            "\"Scalpel\""
        )

        buildConfigField(
            "String",
            "APP_DESC",
            "\"Scalpel trading\""
        )

        buildConfigField(
            "String",
            "APP_REDIRECT_URL",
            "\"scalpel-wc://request\""
        )

        buildConfigField(
            "String", "REOWN_PROJECT_ID", "\"882d3398012401b6a598b7a245adff21\""
        )

        buildConfigField(
            "String", "SCALPEL_HOST", "\"https://trade-scalpel.com\""
        )

        buildConfigField(
            "String", "SCALPEL_API_URL", "\"https://trade-scalpel.com/api/\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
        compose = true
    }
}

dependencies {
    // reown
    implementation(platform(libs.reown.bom))
    implementation(libs.reown.core)
    implementation(libs.reown.appkit)

    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.core)

    // Navigation & Coroutines
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)  // заменили kapt на ksp

    // Network
    implementation(libs.bundles.network)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
