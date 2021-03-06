# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/willi/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#error : Note: the configuration refers to the unknown class 'com.google.vending.licensing.ILicensingService'
#solution : @link http://stackoverflow.com/a/14463528
-dontnote com.google.vending.licensing.ILicensingService
-dontnote **ILicensingService

# http://stackoverflow.com/questions/33047806/proguard-duplicate-definition-of-library-class
-dontnote android.support.annotation.Keep
-dontnote org.apache.http.**
-dontnote android.net.http.**

# google proguard rules add this
-dontnote com.google.android.gms.**
-dontnote android.widget.Toolbar
-dontnote android.support.v7.widget.Toolbar
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.**

# zeroturnaround
-keep class org.slf4j.** { *; }
-keepnames class org.slf4j.** { *; }
-dontwarn org.slf4j.**
-dontwarn org.zeroturnaround.zip.**

# squareup.okhttp3
-dontwarn okhttp3.**
-dontnote okhttp3.**
-keep class org.conscrypt.** { *; }
-keepnames class org.conscrypt.** { *; }
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase.** { *; }
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase.** { *; }
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.**
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform.**

# afollestad.material-dialogs
-dontnote com.afollestad.materialdialogs.**

# BouncyCastle
-keep class org.bouncycastle.** { *; }
-dontwarn javax.naming.**

# safetynethelper
-keep class com.scottyab.safetynet.SafetyNetHelper.** { *; }
-keep class com.google.android.gms.safetynet.** { *; }
