import org.jetbrains.kotlin.config.KotlinCompilerVersion
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.kotlin.dsl.*
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

/*repositories {
    mavenCentral()
    jcenter()
    google()
}*/


val versionName = Versions.Android.appVersionName


android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion(Versions.Android.buildToolsVersion)


    defaultConfig {
        applicationId = Config.Application.applicationId

        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)


        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        val booleanType = "Boolean"

        getByName("debug") {
            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, false.toString())
            isDebuggable = true
        }

        create("qa") {

            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, true.toString())

            isShrinkResources = true
            isMinifyEnabled = true
            isUseProguard = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("release") {

            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, true.toString())

            isShrinkResources = true
            isMinifyEnabled = true
            isUseProguard = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }

        applicationVariants.all(object : Action<ApplicationVariant> {
            override fun execute(variant: ApplicationVariant) {
                variant.outputs.all(object : Action<BaseVariantOutput> {
                    override fun execute(output: BaseVariantOutput) {
                        val outputImpl = output as BaseVariantOutputImpl
                        val fileName = "${variant.name.capitalize()}-${versionName}.apk"
                        outputImpl.outputFileName = fileName
                    }
                })
            }
        })
    }

    flavorDimensions("main")
    productFlavors {
        create("free") {
            applicationId = "sk.android.free"
            dimension = "main"
        }
        create("paid") {
            applicationId = "sk.android.paid"
            dimension = "main"

        }
    }


}


val qaImplementation by configurations

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //==================== Kotlin ====================
    implementation(Depends.kotlinStdLib)


    //==================== Support Library ============
    implementation(Depends.Android.supportAppcompat)
    implementation(Depends.Android.constraintLayout)


    //==================== Tests =====================
    androidTestImplementation(Depends.TestLibraries.jUnitRunner)
    androidTestImplementation(Depends.TestLibraries.espressoCore)
    testImplementation(Depends.TestLibraries.jUnit)

}

