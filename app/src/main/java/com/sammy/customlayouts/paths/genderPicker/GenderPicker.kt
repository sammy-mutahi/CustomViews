package com.sammy.customlayouts.paths.genderPicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

class GenderPicker : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutsTheme {
                Column {
                    GenderPickerComponent(
                        modifier = Modifier.fillMaxSize()
                    ) {

                    }
                }
            }
        }
    }
}