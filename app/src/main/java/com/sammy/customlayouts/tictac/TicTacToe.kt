package com.sammy.customlayouts.tictac

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sammy.customlayouts.R
import com.sammy.customlayouts.ui.theme.CustomLayoutsTheme

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier,
    distanceBetween: Dp = 50.dp,
    pathScaleFactor: Float = 7f
) {
    var center by remember {
        mutableStateOf(Offset.Unspecified)
    }

    //path strings
    val firstColumnPathString = stringResource(id = R.string.tic_tac_toe_grid_path)
    val secondColumPathString = stringResource(id = R.string.col_second_line_path)
    val firstRowPathString = stringResource(id = R.string.row_first_line_path)
    val secondRowPathString = stringResource(id = R.string.row_second_line_path)
    val circlePathString = stringResource(id = R.string.circle_path)
    val xRightDiagonalPathString = stringResource(id = R.string.x_right_diagonal)
    val xLeftDiagonalPathString = stringResource(id = R.string.x_left_diagonal_path)

    //real paths
    val firstRowPath = remember {
        PathParser().parsePathString(firstRowPathString).toPath()
    }
    val secondRowPath = remember {
        PathParser().parsePathString(secondRowPathString).toPath()
    }
    val firstColumnPath = remember {
        PathParser().parsePathString(firstColumnPathString).toPath()
    }
    val secondColumnPath = remember {
        PathParser().parsePathString(secondColumPathString).toPath()
    }
    val circlePath = remember {
        PathParser().parsePathString(circlePathString).toPath()
    }
    val xRightDiagonalPath = remember {
        PathParser().parsePathString(xRightDiagonalPathString).toPath()
    }
    val xLeftDiagonalPath = remember {
        PathParser().parsePathString(xLeftDiagonalPathString).toPath()
    }

    //path bounds
    val firstRowPathBound = remember {
        firstRowPath.getBounds()
    }
    val secondRowPathBound = remember {
        secondRowPath.getBounds()
    }
    val firstColumnPathBound = remember {
        firstColumnPath.getBounds()
    }
    val secondColumnPathBound = remember {
        secondColumnPath.getBounds()
    }

    //translationalOffsets
    var firstRowTranslationalOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var secondRowTranslationalOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var firstColumnTranslationalOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var secondColumnTranslationalOffset by remember {
        mutableStateOf(Offset.Zero)
    }



    Canvas(modifier = modifier) {
        center = this.center

        firstColumnTranslationalOffset = Offset(
            x = center.x - firstColumnPathBound.width * pathScaleFactor - distanceBetween.toPx() / 2f,
            y = center.y - pathScaleFactor * firstColumnPathBound.height / 2F
        )
        secondColumnTranslationalOffset = Offset(
            x = center.x + distanceBetween.toPx() / 2f,
            y = center.y - pathScaleFactor * secondColumnPathBound.height / 2f
        )

        firstRowTranslationalOffset = Offset(
            x = center.x - firstRowPathBound.width * pathScaleFactor - distanceBetween.toPx() / 2f,
            y = center.y - pathScaleFactor * firstRowPathBound.height / 2f
        )

        secondRowTranslationalOffset = Offset(
            x = center.x + distanceBetween.toPx() / 2f,
            y = center.y - pathScaleFactor * secondRowPathBound.height / 2f
        )

        /*translate(
            left = firstColumnTranslationalOffset.x,
            top = firstColumnTranslationalOffset.y
        ) {
            scale(
                scale = pathScaleFactor,
                pivot = firstColumnPathBound.topLeft
            ){
                drawPath(
                    path = firstColumnPath,
                    color = Color.Black
                )
            }
        }*/

        drawPath(
            path = firstColumnPath,
            color = Color.Black
        )

    }

}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    CustomLayoutsTheme {
        TicTacToe(modifier = Modifier.fillMaxSize())
    }
}