package com.sammy.customlayouts

sealed class LineType{
    object Normal: LineType()
    object FiveStep: LineType()
    object Tenstep: LineType()
}
