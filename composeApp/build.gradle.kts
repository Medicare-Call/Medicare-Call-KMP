import java.util.Properties
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.services)
}

detekt {
    buildUponDefaultConfig = true
    toolVersion = libs.versions.detekt.get()
    config.setFrom(files("$rootDir/detekt-config.yml"))
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose Multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            // Navigation (KMP)
            implementation(libs.navigation.compose)

            // Lifecycle ViewModel (KMP)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.lifecycle.runtime.compose)

            // Ktor / Ktorfit
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.response)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // DataStore
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)

            // Kotlin libraries
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)

            // Koin annotations
            implementation(libs.koin.annotations)
        }

        androidMain.dependencies {
            // Ktor Android engine
            implementation(libs.ktor.client.okhttp)

            // Koin Android
            implementation(libs.koin.android)

            // AndroidX
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.androidx.core.ktx)
        }

        iosMain.dependencies {
            // Ktor iOS engine
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.konkuk.medicarecall"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.konkuk.medicarecall"
        minSdk = 26
        targetSdk = 36
        versionCode = 10
        versionName = "0.1.9"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties().apply {
            val localPropertiesFile = project.rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                load(localPropertiesFile.inputStream())
            }
        }

        buildConfigField("String", "BASE_URL", "\"${properties["base.url"] ?: ""}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.konkuk.medicarecall.resources"
    generateResClass = always
}

dependencies {
    // Detekt formatting plugin
    detektPlugins(libs.detekt.formatting)

    // Firebase (Android only)
    add("androidMainImplementation", platform(libs.firebase.bom))
    add("androidMainImplementation", libs.google.firebase.analytics)
    add("androidMainImplementation", libs.firebase.messaging)

    // Koin KSP
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)

    // Ktorfit KSP (plugin does not auto-configure for kspCommonMainMetadata in KMP)
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosX64", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
    add("kspIosSimulatorArm64", libs.ktorfit.ksp)
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "true")
}

// Make KSP-generated code from commonMain available to all source sets
kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

// Remove duplicate _defaultModule/defaultModule from commonMain KSP output to avoid
// conflict with Android KSP output (Android KSP already generates a complete defaultModule)
tasks.matching { it.name == "kspCommonMainKotlinMetadata" }.configureEach {
    doLast {
        fileTree("build/generated/ksp/metadata/commonMain/kotlin") {
            include("**/KoinDefault*.kt")
        }.forEach { file ->
            val content = file.readText()
            val truncateAt = content.indexOf("\npublic val _defaultModule")
            if (truncateAt != -1) {
                file.writeText(content.substring(0, truncateAt) + "\n")
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")

    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "11"
}

// composeApp/build.gradle.kts

tasks.configureEach {
    // Debug 빌드뿐만 아니라 Release 빌드에서도 같은 에러가 발생하는 것을 방지합니다.
    if (name.startsWith("ksp") && name.endsWith("KotlinAndroid")) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
