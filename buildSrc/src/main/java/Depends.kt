object Depends {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidGradlePluginVersion}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object Android {
        const val supportAnnotations = "androidx.annotation:annotation:${Versions.androidSupportVersion}"
        const val supportAppcompat = "androidx.appcompat:appcompat:${Versions.androidSupportVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    }

    object TestLibraries {
        const val jUnit = "junit:junit:${Versions.junitVersion}"
        const val jUnitRunner = "androidx.test:runner:${Versions.junitRunnerVersion}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    }

}