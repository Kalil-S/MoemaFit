plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.moemafit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moemafit"
        minSdk = 24
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
    applicationVariants.all {
        val variant = this
        outputs.all {
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl

            // Define o padrão do nome: Ex: MoemaFit_release_v1.0.apk
            output.outputFileName = "MoemaFit_${variant.name}_v${variant.versionName}.apk"
        }

        dependencies {

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.material)
            implementation(libs.androidx.activity)
            implementation(libs.androidx.constraintlayout)
            testImplementation(libs.junit)
            androidTestImplementation(libs.androidx.junit)
            implementation(libs.androidx.cardview)
            androidTestImplementation(libs.androidx.espresso.core)
        }
    }
}