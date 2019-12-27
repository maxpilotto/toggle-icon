# ToggleIcon
An ImageView that can be toggled like a CheckBox

<img src="https://raw.github.com/maxpilotto/toggle-icon/master/.github/g1.gif" width="400">

## Getting started
In your project's `build.gradle`
```gradle
repositories {
	maven { url "https://jitpack.io" }
}
```

In your modules's `build.gradle`
```gradle 
dependencies {
    implementation 'com.github.maxpilotto:toggle-icon:$latest_version'
}
```

## Usage

```xml
<com.maxpilotto.toggleicon.ToggleIcon
    android:id="@+id/toggle"
    android:layout_width="24dp"
    android:layout_height="24dp"
    app:uncheckedResource="@drawable/eye_off"
    app:checkedResource="@drawable/eye"
    app:checkedTint="@color/colorPrimary"
    app:uncheckedTint="@color/colorAccent"/>
```
```java
toggle.setCheckedTintRes(R.color.colorPrimary)
toggle.setUncheckedTintRes(R.color.colorAccent)
toggle.setCheckedRes(R.drawable.eye)
toggle.setUncheckedRes(R.drawable.eye_off)

toggle.onToggle = { b ->
    password.inputType = if (b)
        InputType.TYPE_CLASS_TEXT
    else
        InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
}
```

# License
```
Copyright 2018 Max Pilotto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
