buildscript {
    extra.apply{
        set("kotlinVersion", "1.5.0")
    }
    val supportLibraryVersion = extra.get("kotlinVersion") as String

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}