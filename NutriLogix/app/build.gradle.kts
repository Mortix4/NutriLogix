plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.nutrilogix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nutrilogix"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencies {

        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.9.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("com.google.android.gms:play-services-maps:18.1.0")
        implementation("com.github.bumptech.glide:glide:4.13.0")
        implementation("io.github.farshidroohi:lineGraph:1.0.2")
        implementation("androidx.recyclerview:recyclerview:1.2.1")
        //implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        val room_version = "2.6.1"
        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")

        val lottieVersion = "6.3.0"
        implementation("com.airbnb.android:lottie:$lottieVersion")
    }
}