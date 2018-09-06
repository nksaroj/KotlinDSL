import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.setValue
import org.gradle.kotlin.dsl.repositories
buildscript {

    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Depends.BuildPlugins.androidPlugin)
        classpath(Depends.BuildPlugins.kotlinPlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
