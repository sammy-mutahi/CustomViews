package com.sammy.customlayouts.tictac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

class TicTacToeGame : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutsTheme {
                TicTacToe(modifier = Modifier.fillMaxSize())
            }
        }
    }
}