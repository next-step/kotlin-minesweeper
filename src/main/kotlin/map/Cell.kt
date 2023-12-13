package map

sealed class Cell
class None(val x: Int, val y: Int, var mineCnt: Int = 0) : Cell()

object Mine : Cell()
