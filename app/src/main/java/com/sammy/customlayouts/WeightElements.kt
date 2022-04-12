package com.sammy.customlayouts

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import kotlin.math.*

@Composable
fun Scale(
    modifier: Modifier = Modifier,
    style: ScaleStyle = ScaleStyle(),
    minweight: Int = 50,
    maxweight: Int = 100,
    initialWeight: Int = 60,
    onWeightChange: (Int) -> Unit
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

    var dragStartAngle by remember {
        mutableStateOf(0f)
    }

    var oldAngle by remember {
        mutableStateOf(angle)
    }

    Canvas(modifier = modifier
        .pointerInput(true){
            detectDragGestures(
                onDragStart = {offset ->
                    dragStartAngle = -atan2(
                        circleCenter.x - offset.x,
                        circleCenter.y - offset.y
                    ) * (180f / PI.toFloat())
                },
                onDragEnd = {
                    oldAngle = angle
                }
            ) { change, _ ->
                //current touch position
                val touchAngle = -atan2(
                    circleCenter.x - change.position.x,
                    circleCenter.y - change.position.y
                ) * (180f / PI.toFloat())
                val newAngle = oldAngle + (touchAngle - dragStartAngle)

                angle = newAngle.coerceIn(
                    minimumValue = initialWeight - maxweight.toFloat(),
                    maximumValue = initialWeight - minweight.toFloat()
                )

                onWeightChange.invoke((initialWeight - angle).toInt())
            }
        }
    ) {
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
                        60f,
                        0f, 0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }
        //Draw lines
        for (i in minweight..maxweight) {
            val angleInRad = (i - initialWeight + angle - 90) * (PI / 180f).toFloat()
            val lineType = when {
                i % 10 == 0 -> LineType.Tenstep
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }
            val lineLength = when (lineType) {
                LineType.Normal -> style.normalLineLength.toPx()
                LineType.FiveStep -> style.fiveStepLineLength.toPx()
                LineType.Tenstep -> style.tenStepLineLength.toPx()
            }
            val lineColor = when (lineType) {
                LineType.Normal -> style.normalLineColor
                LineType.FiveStep -> style.fiveStepLineColor
                LineType.Tenstep -> style.tenStepLineColor
            }
            val lineStart = Offset(
                x = (outerRadius - lineLength) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius - lineLength) * sin(angleInRad) + circleCenter.y
            )
            val lineEnd = Offset(
                x = (outerRadius) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius) * sin(angleInRad) + circleCenter.y
            )
            drawContext.canvas.nativeCanvas.apply {
                if (lineType is LineType.Tenstep) {
                    val textRadius = outerRadius - lineLength - 5.dp.toPx() - style.textSize.toPx()
                    val x = textRadius * cos(angleInRad) + circleCenter.x
                    val y = textRadius * sin(angleInRad) + circleCenter.y
                    withRotation(
                        degrees = angleInRad * (180f / PI.toFloat()) + 90f,
                        pivotX = x,
                        pivotY = y
                    ) {
                        drawText(
                            abs(i).toString(),
                            x,
                            y,
                            Paint().apply {
                                textSize = style.textSize.toPx()
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

            val middleTop = Offset(
                x = circleCenter.x,
                y = circleCenter.y - innerRadius - style.scaleIndicatorLength.toPx()
            )

            val bottomLeft = Offset(
                x = circleCenter.x - 4f,
                y = circleCenter.y - innerRadius
            )

            val bottomRight = Offset(
                x = circleCenter.x + 4f,
                y = circleCenter.y - innerRadius
            )
            val indicator = Path().apply {
                moveTo(middleTop.x, middleTop.y)
                lineTo(bottomLeft.x, bottomLeft.y)
                lineTo(bottomRight.x, bottomRight.y)
                lineTo(middleTop.x, middleTop.y)
            }
            drawPath(
                path = indicator,
                color = style.scaleIndicatorColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var weight by remember {
        mutableStateOf(80)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scale(
            style = ScaleStyle(
                scaleWidth = 150.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
        ) {
            weight = it
        }
    }
}