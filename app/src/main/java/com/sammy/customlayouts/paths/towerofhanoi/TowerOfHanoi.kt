package com.sammy.customlayouts.paths.towerofhanoi

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

@Composable
fun TowerOfHanoi(
    onPlayerWin: (String) -> Unit = {},
    onNewRound: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    val isGameRunning by remember {
        mutableStateOf(true)
    }

    val gameState by remember {
        mutableStateOf(defaultGameState())
    }

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Black,
            start = Offset(size.width / 5f, size.height - 400f),
            end = Offset(size.width / 5f, size.height),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Black,
            start = Offset(2 * size.width / 4f, size.height - 400f),
            end = Offset(2 * size.width / 4f, size.height),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Black,
            start = Offset(4 * size.width / 5f, size.height - 400f),
            end = Offset(4 * size.width / 5f, size.height),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round
        )

        drawLine(
            color = Color.Black,
            start = Offset(0f, size.height),
            end = Offset(size.height, size.height),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

private fun defaultGameState(): Array<Disk> {
    return arrayOf(Disk.DiskOne, Disk.DiskTwo, Disk.DiskThree)
}

@Preview(showBackground = true)
@Composable
fun TowerOfHanoiPreview() {
    CustomLayoutsTheme {
        TowerOfHanoi()
    }
}