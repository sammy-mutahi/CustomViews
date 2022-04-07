package com.sammy.customlayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutsTheme {
                MyCanvas()
            }
        }
    }
}

@Composable
fun MyCanvas(){
    Canvas(modifier = Modifier
        .padding(20.dp)
        .size(300.dp)){

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f,100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 3.dp.toPx(),
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red,Color.Yellow),
                center = center,
                radius = 100f
            ),
            radius = 100f,
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = Offset(100f,500f),
            size = Size(200f,200f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )
        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f,100f),
            size = Size(200f,300f)
        )
        drawLine(
            color = Color.Cyan,
            start = Offset(300f,700f),
            end = Offset(700f,700f),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomLayoutsTheme {
        MyCanvas()
    }
}