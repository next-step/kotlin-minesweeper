package minesweeper.domain

class MineBoardRow(val mineCells: Array<Cell>) {
    val width: Int
        get() = mineCells.size

    fun plantMine(width: Int) {
        mineCells[width] = MineCell()
    }

    constructor(width: Int) : this(Array(width) { CleanCell() })
}
