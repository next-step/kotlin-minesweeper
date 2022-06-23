package minesweeper.domain.board

import minesweeper.domain.cell.Cells

class MineBoard private constructor(
    val cells: Cells,
    private var numberOfRemainingEmptyCells: Int
) {

    fun open(x: Int, y: Int): BoardStatus {
        val cell = cells.first { it.position.x == x && it.position.y == y }
        val boardStatus = cells.open(cell.position)
        if (boardStatus == BoardStatus.SAFE) {
            numberOfRemainingEmptyCells -= 1
        }
        return if (isClear()) {
            BoardStatus.CLEAR
        } else {
            boardStatus
        }
    }

    private fun isClear() = numberOfRemainingEmptyCells == 0

    companion object {
        fun of(
            width: Int,
            height: Int,
            numberOfMines: Int,
            mineMaker: MineMaker
        ): MineBoard {
            val size = width * height
            val numberOfEmptyCells = size - numberOfMines

            require(width >= 0 && height >= 0) { "property must be zero or positive." }
            require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }

            val cells = Cells.of(width, height, numberOfMines, mineMaker)
            NearbyMineCounter.count(cells)
            return MineBoard(cells, numberOfEmptyCells)
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
