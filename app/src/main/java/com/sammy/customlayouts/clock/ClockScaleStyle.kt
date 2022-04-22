package com.sammy.customlayouts.clock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockScaleStyle(
    val scaleWidth: Dp = 150.dp,
    val radius:Dp = 80.dp,
    val nomarmalLineColor: Color = Color.LightGray,
    val fiveStepLineColor:Color = Color.Black,
    val normalLineLength:Dp = 10.dp,
    val fiveStepLineLength:Dp = 15.dp,
    val minuteHandColor:Color = Color.Black,
    val hourHandColor:Color = Color.Red,
    val minuteHandLength:Dp= 50.dp,
    val hourHandLength:Dp = 30.dp
)