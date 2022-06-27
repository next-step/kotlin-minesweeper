package minesweeper.domain

open class Cell(var mineCountAround: Int = 0, val isMineCell: Boolean = false)

class NumberCell(mineCountAround: Int) : Cell(mineCountAround)
class MineCell : Cell(isMineCell = true)
