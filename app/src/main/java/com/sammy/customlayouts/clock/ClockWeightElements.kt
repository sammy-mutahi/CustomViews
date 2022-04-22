package com.sammy.customlayouts.clock

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.LineType
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Scale(
    modifier: Modifier = Modifier,
    minTime:Int = 1,
    maxTime:Int = 60,
    initialTime:Int = 6,
    style: ClockScaleStyle = ClockScaleStyle()
) {
    val radius = style.radius
    val scaleWidth = style.scaleWidth

    var center by remember {
        mutableStateOf(Offset.Zero)
    }

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var angle by remember {
        mutableStateOf(0f)
    }

    Canvas(modifier = modifier) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())
        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f
        val innerRadius = radius.toPx() - scaleWidth.toPx() / 2f

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x, circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = Color.WHITE
                    setStyle(
                        Paint.Style.STROKE
                    )
                    setShadowLayer(
                        200f,
                        0f, 0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        for (i in minTime..maxTime){
            val angleInRad = (i - initialTime + angle) * (PI/ 30).toFloat()
            val lineType = when{
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }

            val lineLength = when(lineType){
                LineType.FiveStep -> style.fiveStepLineLength.toPx()
                else -> style.normalLineLength.toPx()
            }
            val lineColor = when(lineType){
                LineType.FiveStep -> style.fiveStepLineColor
                else -> style.nomarmalLineColor
            }

            val lineStart = Offset(
                x = (outerRadius - lineLength) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius - lineLength) * sin(angleInRad) + circleCenter.y
            )

            val lineEnd = Offset(
                x = outerRadius * cos(angleInRad) + circleCenter.x,
                y = outerRadius * sin(angleInRad) + circleCenter.y
            )

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Box(modifier = Modifier.fillMaxSize()
    ) {
        Scale(
            modifier = Modifier
                .align(Alignment.Center)
                .height(300.dp)
        )
    }
}