package com.sammy.customlayouts.paths

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

class PathActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimplePaths()
        }
    }
}

@Composable
fun PathAnimation(){

}

@Composable
fun PathOperations(){
    Canvas(modifier = Modifier.fillMaxSize()){
        val squareWithoutOperations = Path().apply {
            addRect(Rect(Offset(200f,200f), Size(200f,200f)))
        }
        val circle = Path().apply {
            addOval(Rect(Offset(200f,200f),100f))
        }
        val pathWithOperation = Path().apply {
            op(squareWithoutOperations,circle, PathOperation.Xor)
        }
        drawPath(
            path = squareWithoutOperations,
            color= Color.Red,
            style = Stroke(width = 2.dp.toPx())
        )
        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(width = 2.dp.toPx())
        )
        drawPath(
            path = pathWithOperation,
            color = Color.Green,
        )
    }
}

@Composable
private fun SimplePaths(){
    Canvas(modifier = Modifier.fillMaxSize()){
        val path = Path().apply {
            moveTo(100f, 100f)
            lineTo(100f, 500f)
            lineTo(500f,500f)
            /*quadraticBezierTo(
                800f,
                300f,
                500f,100f
            )*/
            cubicTo(800f,500f,800f,100f,500f,100f)
           // close()
        }
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    CustomLayoutsTheme{
        PathOperations()
    }
}
