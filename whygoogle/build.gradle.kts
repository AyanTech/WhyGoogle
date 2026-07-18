import org.gradle.api.publish.maven.MavenPublication

plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}
android {
    namespace = "ir.ayantech.whygoogle"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildFeatures {
        viewBinding = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
//    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.nonfinalviewpager2)
    api(libs.gson)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.AyanTech"
            artifactId = "whygoogle"
            version = "2.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Ayan Android UI tools")
                description.set("")
                url.set("https://github.com/AyanTech/WhyGoogle")
            }
        }
    }
}
