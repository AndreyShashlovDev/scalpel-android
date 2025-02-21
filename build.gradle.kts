// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    plugins.withId("com.android.library") {
        extensions.configure<com.android.build.gradle.LibraryExtension> {
            packaging {
                resources {
                    pickFirsts.add("META-INF/versions/9/OSGI-INF/MANIFEST.MF")
                    pickFirsts.add("META-INF/MANIFEST.MF")
                    pickFirsts.add("META-INF/*.version")
                    pickFirsts.add("META-INF/versions/**")
                }
            }
        }
    }
}
