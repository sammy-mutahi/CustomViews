package com.sammy.customlayouts.tictac

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import com.sammy.customlayouts.R

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier,
) {
    var center by remember {
        mutableStateOf(Offset.Unspecified)
    }

    //path strings
    val firstColumnPathString = stringResource(id = R.string.col_first_line_path)
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


}