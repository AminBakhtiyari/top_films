import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.konan.properties.propertyString
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        val tmdbApiKey: String? = gradleLocalProperties(rootDir).propertyString("tmdb_api_key", "")

        release {
            buildConfigField("String", "BASE_API_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "TMDB_API_KEY", tmdbApiKey ?: "")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            buildConfigField("String", "BASE_API_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "TMDB_API_KEY", tmdbApiKey ?: "")
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
}

kapt {
    correctErrorTypes = true
}

dependencies {

    // hilt (DI)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)

    // retrofit
    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverter)

    // okhttp3
    implementation(Deps.okhttpLogging)
    implementation(Deps.okhttpUrlConnection)

    // Gson
    implementation(Deps.gson)
}