// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.plugin.serialization) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}