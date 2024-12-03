# AutoStartPermission Library

## 🚀 Overview

AutoStartPermission is a lightweight Android library that simplifies requesting auto-start permissions for devices with restricted background activities, particularly for manufacturers like Xiaomi, Vivo, Oppo, and others.

## 🔒 Problem Solved

Many Android manufacturers implement aggressive battery-saving mechanisms that prevent apps from running in the background. This library provides a unified approach to:
- Detect manufacturer-specific restrictions
- Request auto-start permissions
- Improve app background functionality

## 🏭 Supported Manufacturers
- Xiaomi (MIUI)
- Vivo
- Oppo
- Huawei
- Letv
- Samsung
- Other custom Android ROMs

## 📦 Installation

### Gradle Dependency
```groovy
dependencies {
    implementation 'com.github.spahwa26:AutoStartPermission:1.0.0'
}
```

### Repository Setup
Add the following to your project's `build.gradle`:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

## 🛠 Usage

### Basic Implementation
```kotlin
class YourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check and request auto-start permission
        PermissionActivity.start(context, "Dialog title", "Dialog message", "Negative button text", "Later button text", "Done button text")
    }
}
```

## 🔍 Key Features
- Manufacturer-specific permission handling
- Simple, one-line permission request
- Callback support for permission results
- Minimal integration overhead
- Works across different Android versions

## 📱 Compatibility
- Minimum SDK: Android 5.0 (Lollipop)
- Supports latest Android versions
- Works with most custom Android ROMs

## 🧪 Best Practices
- Request permission during app first launch
- Provide clear explanation to users
- Handle both permission granted and denied scenarios

## ⚠️ Limitations
- Some manufacturers may have unique restrictions
- User can manually disable auto-start later
- Not a guarantee of background execution

## 🤝 Contributing
1. Fork the repository
2. Create feature branch
3. Implement improvements
4. Submit pull request

### Contribution Guidelines
- Improve manufacturer support
- Add more detailed manufacturer checks
- Enhance documentation
- Write comprehensive tests

## 🔧 Troubleshooting
- Ensure proper context is used
- Check Android manifest permissions
- Verify manufacturer-specific requirements


## 📞 Support
For issues or feature requests, please open a GitHub issue.
