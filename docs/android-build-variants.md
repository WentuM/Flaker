# Usage

## Setting Up Flaker with Multiple Variants on Android

When integrating Flaker into an Android project that includes more than just the standard debug and release variants, it's essential to configure Flaker to handle these additional variants properly. 
One crucial aspect of this configuration is using matchingFallbacks in your project's build.gradle file. Here's why and how you should do it:

### Why Use matchingFallbacks?

Android projects often include custom build types or flavors, which may not directly match those of the dependencies they rely on. When Flaker attempts to match variants between your app and its dependencies, it may encounter situations where a direct match is not possible due to differences in build types or flavors. In such cases, matchingFallbacks provides a mechanism to specify alternative matches, ensuring proper variant resolution.

### Adding matchingFallbacks to Your Project

To ensure Flaker handles multiple variants correctly in your project, you should add matchingFallbacks to your project's build.gradle file, specifying fallback build types or flavors that Flaker should try when direct matches are unavailable. Here's how you can do it:

#### 1) Build Type
```kotlin
    android {
        buildTypes {
            release {
            }
            qa {
                matchingFallbacks = ['debug', 'release']
            }
        }
    }
```