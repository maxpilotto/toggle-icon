package com.maxpilotto.toggleicondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}
