package com.sammy.customlayouts.clock

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.withRotation
import com.sammy.customlayouts.LineType
import java.time.Clock
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    seconds:Float = 0f,
    minutes:Float = 0f,
    hours:Float = 0f,
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

    Canvas(modifier = modifier.size(radius * 2f)) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())
        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x, circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = android.graphics.Color.WHITE
                    setStyle(
                        Paint.Style.STROKE
                    )
                    setShadowLayer(
                        200f,
                        0f, 0f,
                        android.graphics.Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        for (i in 1..60){
            val angleInRad = (i * (360f / 60f) - 90)* (PI.toFloat() / 180f)
            val lineType = when{
                i % 5 == 0 -> ClockLineType.FiveStep
                else -> ClockLineType.Normal
            }

            val lineLength = when(lineType){
                ClockLineType.FiveStep -> style.fiveStepLineLength.toPx()
                else -> style.normalLineLength.toPx()
            }
            val lineColor = when(lineType){
                ClockLineType.FiveStep -> style.fiveStepLineColor
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
            drawContext.canvas.nativeCanvas.apply {
                if (lineType is ClockLineType.FiveStep){
                    val textRadius = outerRadius - lineLength - 5.dp.toPx() - 18.sp.toPx()
                    val x = textRadius * cos(angleInRad) + circleCenter.x
                    val y = textRadius * sin(angleInRad) + circleCenter.y
                    withRotation(degrees = angleInRad,
                        pivotX = x,
                        pivotY = y){
                        drawText(
                            abs(i/5).toString(),
                            x,
                            y,
                            Paint().apply {
                                textSize = 18.sp.toPx()
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
            }

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx()
            )

            //seconds
            rotate(degrees = seconds * (360f / 60f)){
                drawLine(
                    color = Color.Red,
                    start = circleCenter,
                    end = Offset(circleCenter.x, 20.dp.toPx()),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            //minutes
            rotate(degrees = minutes * (360f / 60f)){
                drawLine(
                    color = Color.Black,
                    start = circleCenter,
                    end = Offset(circleCenter.x, 20.dp.toPx()),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round,
                )
            }
            //hours
            rotate(degrees = hours * (360f / 12f)){
                drawLine(
                    color = Color.Black,
                    start = circleCenter,
                    end = Offset(circleCenter.x, 20.dp.toPx()),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Box(modifier = Modifier.fillMaxSize()
    ) {
        Clock(
            modifier = Modifier
                .align(Alignment.Center)
                .height(300.dp)
        )
    }
}