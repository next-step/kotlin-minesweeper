package minesweeper.domain

class MineBoardRow(val mineCells: Array<Cell>) {
    fun plantMine(width: Int) {
        mineCells[width] = MineCell()
    }

    constructor(width: Int) : this(Array(width) { CleanCell() })
}
