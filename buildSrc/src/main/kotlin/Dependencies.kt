
/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    val androidxCore by lazy { "androidx.core:core-ktx:${Versions.appCompat}" }
    val androidxFragment by lazy { "androidx.fragment:fragment-ktx:${Versions.appCompat}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroid}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okhttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}" }
    val okhttpUrlConnection by lazy { "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttpUrlConnection}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val lifecycleLivedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val lifecycleCommon by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" }
    val lifecycleArch by lazy { "android.arch.lifecycle:extensions:${Versions.lifecycleArch}" }
    val legacy by lazy { "androidx.legacy:legacy-support-v4:${Versions.legacy}" }
    val multidex by lazy { "androidx.multidex:multidex:${Versions.multidex}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
}
