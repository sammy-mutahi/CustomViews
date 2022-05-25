package com.sammy.customlayouts.paths.towerofhanoi

sealed class Disk(val symbol: Int) {
    object DiskOne : Disk(1)
    object DiskTwo : Disk(2)
    object DiskThree : Disk(3)
}
