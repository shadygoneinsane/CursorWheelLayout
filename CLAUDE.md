# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

CursorWheelLayout is an Android library that provides a circular wheel layout where views can be placed on a rotatable wheel. It behaves like a circular ListView where items rotate rather than scroll vertically (without view recycling strategy). The library consists of two main components:

1. **Library Module** (`/library/`): Contains the core `CursorWheelLayout` component
2. **Demo App** (`/app/`): Sample application demonstrating the library usage

## Build Commands

### Standard Android Gradle Commands
```bash
# Build the project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install debug APK to connected device
./gradlew installDebug

# Run tests
./gradlew test

# Run connected tests (requires device/emulator)
./gradlew connectedAndroidTest

# Clean build artifacts
./gradlew clean
```

### Library Publishing
```bash
# Publish library to local Maven repository
./gradlew publishToMavenLocal

# Publish release artifact
./gradlew publishReleasePublicationToMavenRepository
```

## Project Structure

### Core Architecture

**CursorWheelLayout** (`/library/src/main/java/com/cursorwheel/CursorWheelLayout.kt`):
- Main ViewGroup that handles circular layout of child views
- Manages touch events for rotation and selection
- Provides adapter pattern for data binding via `CycleWheelAdapter`
- Supports customizable attributes (angles, colors, dimensions, rotation modes)

**Key Components:**
- `CycleWheelAdapter`: Abstract adapter class for providing data to the wheel
- `OnMenuItemClickListener`: Interface for handling item clicks
- `OnMenuSelectedListener`: Interface for handling item selection changes
- `FlingRunnable`: Handles smooth scrolling animations

### Demo Application Structure

**MainActivity** (`/app/src/main/java/com/cursorwheellayout/MainActivity.kt`):
- Demonstrates three different wheel configurations
- Shows both text and image adapters
- Implements selection and click listeners

**Adapters** (`/app/src/main/java/com/cursorwheellayout/adapter/`):
- `SimpleTextAdapter`: Displays text items in the wheel
- `SimpleImageAdapter`: Displays image items in the wheel

**Data Models** (`/app/src/main/java/com/cursorwheellayout/data/`):
- `MenuItemData`: Data class for text menu items
- `ImageData`: Data class for image menu items

## Key Customization Attributes

The library supports extensive customization through XML attributes:

- `wheelSelectedAngle`: Position on wheel that is considered selected
- `wheelPaddingRadio`: Padding ratio relative to wheel size
- `wheelCenterRadio`: Size ratio of center item
- `wheelItemRadio`: Size ratio of menu items
- `wheelFlingValue`: Sensitivity for fling gestures
- `wheelCursorColor`: Color of the selection cursor
- `wheelCursorHeight`: Height of the selection cursor
- `wheelBackgroundColor`: Background color of the wheel
- `wheelItemRotateMode`: How items rotate (none, inward, outward)

## Development Notes

### Kotlin Configuration
- Uses Kotlin 2.0.21
- Target SDK: 34, Min SDK: 14
- Java compatibility: VERSION_17
- Data binding enabled

### Dependencies
- Core library has minimal dependencies (only AndroidX annotations)
- Demo app uses AndroidX libraries (Core KTX, Material, ConstraintLayout, Lifecycle)

### Testing
- Unit tests: `/app/src/test/` and `/library/src/test/`
- Instrumentation tests: `/app/src/androidTest/`
- Test runner: AndroidJUnitRunner

### Publishing
- Published to JitPack as `com.github.shadygoneinsane:CursorWheelLayout:v2.0.01`
- Maven publication configured in library module
- Version controlled through `build.gradle` files