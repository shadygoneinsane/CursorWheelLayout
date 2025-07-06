# CursorWheelLayout for Jetpack Compose

CursorWheelLayout is a Jetpack Compose library that allows you to arrange items in a rotatable wheel, providing a unique and engaging way for users to select from a list. This library is a reimplementation of the original [CursorWheelLayout](https://github.com/BCsl/CursorWheelLayout) for modern Android development with Jetpack Compose.

## Screenshot

![Compose-1](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZzVna2l6d2M3cWw5eGN6bXNqN3VnaWJsa3l6cW16Z3A0ZzVzZ3h6eCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3oKIPa8xYQY1dG3pks/giphy.gif)

## Setup

To integrate CursorWheelLayout into your Jetpack Compose project, follow these steps:

**Step 1. Add the JitPack repository to your build file**

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2. Add the dependency**

```groovy
dependencies {
    implementation 'com.github.shadygoneinsane:CursorWheelLayout:v3.0.0-compose'
}
```

## Usage

Using the `CursorWheelLayout` in your Jetpack Compose UI is straightforward. Here’s a basic example:

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cursorwheel.compose.CursorWheelLayout

@Composable
fun MyWheelSelector() {
    val items = (1..10).map { "Item $it" }

    CursorWheelLayout(
        items = items,
        modifier = Modifier
    ) { index, item, isSelected ->
        // Your item content here
    }
}
```

### Customizing the Wheel

You can customize the appearance and behavior of the wheel using various parameters:

```kotlin
CursorWheelLayout(
    items = items,
    modifier = Modifier,
    wheelSize = 300.dp,
    itemSize = 80.dp,
    selectedAngle = 90f,
    itemRotationMode = ItemRotationMode.Inward,
    onItemSelected = { index, item ->
        // Handle item selection
    },
    onItemClick = { index, item ->
        // Handle item click
    }
) { index, item, isSelected ->
    // Your item content here
}
```

### Available Parameters

Here are the key parameters you can use to customize the `CursorWheelLayout`:

| Parameter | Type | Description |
|---|---|---|
| `items` | `List<T>` | The list of items to display in the wheel. |
| `modifier` | `Modifier` | The modifier to apply to this layout. |
| `wheelSize` | `Dp` | The size of the wheel. |
| `itemSize` | `Dp` | The size of each item in the wheel. |
| `selectedAngle` | `Float` | The angle at which the selected item is positioned. |
| `itemRotationMode` | `ItemRotationMode` | The rotation mode for the items (`None`, `Inward`, `Outward`). |
| `onItemSelected` | `(index: Int, item: T) -> Unit` | A callback that is invoked when an item is selected. |
| `onItemClick` | `(index: Int, item: T) -> Unit` | A callback that is invoked when an item is clicked. |

## License

Apache License Version 2.0
http://apache.org/licenses/LICENSE-2.0.txt