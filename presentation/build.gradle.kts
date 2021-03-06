plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = "ir.darmasirco.android.client"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        multiDexEnabled = true


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

kapt {
    correctErrorTypes = true
}
dependencies {

    //domain layer
    api(project(mapOf("path" to ":domain")))

    //lang
    implementation(Deps.kotlin)

    //android support
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)
    implementation(Deps.androidxCore)
    implementation(Deps.androidxFragment)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)

    //log
    implementation(Deps.timber)


    // hilt (DI)
    implementation(Deps.hiltAndroid)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Deps.hiltAndroidCompiler)

    // retrofit
    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverter)

    // okhttp3
    implementation(Deps.okhttpLogging)
    implementation(Deps.okhttpUrlConnection)

    // Gson
    implementation(Deps.gson)

    //android lifecycle
    implementation (Deps.lifecycleLivedata)
    implementation (Deps.lifecycleViewModel)
    implementation (Deps.lifecycleCommon)
    implementation (Deps.legacy)
    implementation (Deps.lifecycleArch)

    //multidex
    implementation (Deps.multidex)

    //coil
    implementation (Deps.coil)

    // paging
    implementation(Deps.paging)

    //test
    testImplementation(Deps.junit)
}