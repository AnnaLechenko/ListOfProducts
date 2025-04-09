plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    kotlin("kapt")



}

android {
    namespace = "com.annalech.listofproducts"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.annalech.listofproducts"
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
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.viewModel)
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    //Dagger2
    implementation(libs.dagger2)
//Dagger2 кодогенератор
    ksp(libs.dagger2.compiler)
//Dagger2 аннотации
    ksp(libs.dagger2.android.processor)

}