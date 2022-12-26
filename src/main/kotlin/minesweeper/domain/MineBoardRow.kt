package minesweeper.domain

class MineBoardRow(val mineCells: Array<Cell>) {
    val width: Int
        get() = mineCells.size

    fun plantMine(width: Int) {
        mineCells[width] = MineCell()
    }

    fun increaseNearMineCount(width: Int) {
        val cleanCell = mineCells[width]
        if (cleanCell is CleanCell) {
            cleanCell.nearMineIncrement()
        }
    }

    constructor(width: Int) : this(Array(width) { CleanCell() })
}
