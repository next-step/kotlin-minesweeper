package minesweeper.domain

class MineBoardRow(val mineCells: Array<Cell>) {
    val width: Int
        get() = mineCells.size

    constructor(width: Int) : this(Array(width) { CleanCell() })

    fun plantMine(width: Int) {
        mineCells[width] = MineCell()
    }

    fun incrementNearMineCount(width: Int) {
        val cleanCell = mineCells[width]
        if (cleanCell is CleanCell) {
            cleanCell.nearMineIncrement()
        }
    }
}
