package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine

class MineBoard private constructor(
    val width: Int,
    val height: Int,
    val numberOfMines: Int
) {
    val size = width * height
    lateinit var board: List<Cell>
        private set

    init {
        require(width > 0 && height > 0 && numberOfMines > 0) { "width, height and number of mines must be positive." }
        require(size >= numberOfMines) { "number of mines can't exceed the size of board." }
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

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int) = MineBoard(width, height, numberOfMines)
    }
}
