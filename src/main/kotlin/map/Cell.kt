package map

sealed class Cell
class None(val x: Int, val y: Int) : Cell()
object Mine : Cell()
class Open(val mineCnt: Int) : Cell()
