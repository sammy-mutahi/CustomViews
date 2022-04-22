package com.sammy.customlayouts.clock

sealed class ClockLineType{
    object Normal: ClockLineType()
    object FiveStep: ClockLineType()
}