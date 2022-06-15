package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine

class MineBoard(
    val width: Int,
    val height: Int,
    val numberOfMines: Int
) {
    val size = width * height
    lateinit var board: List<Cell>
        private set

    init {
        require(width > 0 && height > 0) { "width and height must be positive." }
        require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }
        build()
    }

    private fun build() {
        val mineIndices = size.toShuffledMineIndices(numberOfMines)
        board = List(size) {
            val x = it % width
            val y = it / width
            if (it in mineIndices) {
                Mine(x, y)
            } else {
                Empty(x, y)
            }
        }
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int) = (0 until this).shuffled().subList(0, numberOfMines)
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
