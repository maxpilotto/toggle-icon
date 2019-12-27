/*
 * Copyright 2018 Max Pilotto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maxpilotto.toggleicon

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * Image that can be toggled like a checkbox, the toggled values are the icon and the tint
 */
class ToggleIcon : ImageView {
    /**
     * Callback invoked when this view is toggled
     */
    var onToggle: (Boolean) -> Unit = {}

    /**
     * Checked value
     */
    var isChecked: Boolean
        set(value) {
            setImageDrawable(if (value) checkedDrawable else uncheckedDrawable)
            setColorFilter(if (value) checkedTint else uncheckedTint, PorterDuff.Mode.SRC_IN)

            field = value
        }

    /**
     * Tint for when the toggle is checked
     */
    @ColorInt
    var checkedTint: Int
        set(value) {
            field = value

            refresh()
        }

    /**
     * Tint for when the toggle is not checked
     */
    @ColorInt
    var uncheckedTint: Int
        set(value) {
            field = value

            refresh()
        }

    /**
     * Image drawable for when the toggle is checked
     */
    var checkedDrawable: Drawable
        set(value) {
            field = value

            refresh()
        }

    /**
     * Image drawable for when the toggle is not checked
     */
    var uncheckedDrawable: Drawable
        set(value) {
            field = value

            refresh()
        }

    /**
     * Whether or not this should show a ripple effect
     */
    var hasRipple: Boolean
        set(value) {
            if (value) {
                with(TypedValue()) {
                    context.theme.resolveAttribute(
                        R.attr.selectableItemBackgroundBorderless,
                        this,
                        true
                    )
                    setBackgroundResource(resourceId)
                }
            } else {
                background = null
            }

            field = value
        }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val primary = with(TypedValue()) {
            context.theme.resolveAttribute(R.attr.colorPrimary, this, true)
            data
        }

        with(context.obtainStyledAttributes(attrs, R.styleable.ToggleIcon, 0, defStyleAttr)) {
            checkedTint = getColor(R.styleable.ToggleIcon_checkedTint, primary)
            uncheckedTint = getColor(R.styleable.ToggleIcon_uncheckedTint, primary)
            checkedDrawable = context.resources.getDrawable(
                getResourceId(
                    R.styleable.ToggleIcon_checkedResource,
                    R.drawable.android
                )
            )
            uncheckedDrawable = context.resources.getDrawable(
                getResourceId(
                    R.styleable.ToggleIcon_uncheckedResource,
                    R.drawable.android
                )
            )
            isChecked = getBoolean(R.styleable.ToggleIcon_checked, false)
            hasRipple = getBoolean(R.styleable.ToggleIcon_hasRipple, true)

            recycle()
        }

        setOnClickListener {
            toggle()
            onToggle(isChecked)
        }
    }

    /**
     * Refreshes the resources
     */
    private fun refresh() {
        isChecked = isChecked
    }

    /**
     * Sets the image drawable from a drawable resource
     */
    fun setCheckedRes(@DrawableRes drawableRes: Int) {
        checkedDrawable = context.resources.getDrawable(drawableRes)
    }

    /**
     * Sets the image drawable from a drawable resource
     */
    fun setUncheckedRes(@DrawableRes drawableRes: Int) {
        uncheckedDrawable = context.resources.getDrawable(drawableRes)
    }

    /**
     * Sets the tint color from a color resource
     */
    fun setCheckedTintRes(@ColorRes colorRes: Int) {
        checkedTint = context.resources.getColor(colorRes)
    }

    /**
     * Sets the tint color from a color resource
     */
    fun setUncheckedTintRes(@ColorRes colorRes: Int) {
        uncheckedTint = context.resources.getColor(colorRes)
    }

    /**
     * Toggles the value of [isChecked]
     */
    fun toggle() {
        isChecked = !isChecked
    }
}