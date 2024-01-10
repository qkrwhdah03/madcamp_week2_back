plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.week2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.week2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro" ,
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    // Kakao API
    implementation("com.kakao.sdk:v2-user:2.19.0") // 카카오 로그인
    //implementation("com.kakao.sdk:v2-talk:2.19.0") // 친구, 메시지(카카오톡)
    //implementation("com.kakao.sdk:v2-share:2.19.0") // 메시지(카카오톡 공유)
    //implementation("com.kakao.sdk:v2-navi:2.19.0") // 카카오내비
    //implementation("com.kakao.sdk:v2-cert:2.19.0") // 카카오 인증서비스
}