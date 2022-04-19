package com.sammy.customlayouts.clock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockScaleStyle(
    val scaleWidth: Dp = 50.dp,
    val radius:Dp = 220.dp,
    val nomarmalLineColor: Color = Color.LightGray,
    val fiveStepLineColor:Color = Color.Black,
    val normalLineLength:Dp = 15.dp,
    val fiveStepLineLength:Dp = 25.dp,
    val minuteHandColor:Color = Color.Black,
    val hourHandColor:Color = Color.Red,
    val minuteHandLength:Dp= 50.dp,
    val hourHandLength:Dp = 30.dp
)