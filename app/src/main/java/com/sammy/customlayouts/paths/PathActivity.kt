package com.sammy.customlayouts.paths

import android.graphics.Paint
import android.graphics.PathMeasure
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme
import kotlin.math.PI
import kotlin.math.atan2

class PathActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathAnimation()
        }
    }
}

@Composable
fun PathAnimation() {
    val pathPortion = remember {
        androidx.compose.animation.core.Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = true) {
        pathPortion.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 5000
            )
        )
    }
    val path = Path().apply {
        moveTo(100f, 100f)
        quadraticBezierTo(400f, 400f, 100f, 400f)
    }
    val outPath = android.graphics.Path()
    val pos = FloatArray(2)
    val tan = FloatArray(2)
    PathMeasure().apply {
        setPath(path.asAndroidPath(), false)
        getSegment(0f, pathPortion.value * length, outPath,true)
        getPosTan(pathPortion.value * length,pos,tan)

    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val x = pos[0]
        val y = pos[1]
        val degrees = -atan2(tan[0],tan[1])* (180f / PI.toFloat()) - 180f

        rotate(degrees = degrees, pivot = Offset(x,y)){
            drawPath(
                path = Path().apply {
                    moveTo(x, y-30f)
                    lineTo(x-30f, y+60f)
                    lineTo(x+30f, y+60f)
                    close()
                },
                color = Color.Red
            )
        }

        /*drawPath(
            path = outPath.asComposePath(),
            color = Color.Red,
            style = Stroke(width = 5.dp.toPx())
        )*/

    }
}

@Composable
fun PathOperations() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val squareWithoutOperations = Path().apply {
            addRect(Rect(Offset(200f, 200f), Size(200f, 200f)))
        }
        val circle = Path().apply {
            addOval(Rect(Offset(200f, 200f), 100f))
        }
        val pathWithOperation = Path().apply {
            op(squareWithoutOperations, circle, PathOperation.Xor)
        }
        drawPath(
            path = squareWithoutOperations,
            color = Color.Red,
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
fun PathEffects(){
    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10000f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing )
        )
    )
    Canvas(modifier = Modifier.fillMaxSize()){
        val path = Path().apply {
            moveTo(100f,100f)
            cubicTo(100f,300f,600f,700f,600f,1100f)
        }
        drawPath(
            path = path,
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(50f, 30f),
                    phase = phase
                )
            )
        )
    }
}

@Composable
fun PathText() {
    val path = android.graphics.Path().apply {
        moveTo(200f, 800f)
        quadTo(600f, 400f, 1000f, 800f)
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.apply {
            drawTextOnPath(
                "Hello World",
                path,
                30f,
                50f,
                Paint().apply {
                    color = android.graphics.Color.RED
                    textSize = 70f
                }
            )
        }
    }
}

@Composable
private fun SimplePaths() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            lineTo(100f, 500f)
            lineTo(500f, 500f)
            lineTo(500f, 100f)
            lineTo(100f, 100f)
            /*quadraticBezierTo(
                800f,
                300f,
                500f,100f
            )*/
           // cubicTo(800f, 500f, 800f, 100f, 500f, 100f)
            // close()
        }
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CustomLayoutsTheme {
        PathText()
    }
}
