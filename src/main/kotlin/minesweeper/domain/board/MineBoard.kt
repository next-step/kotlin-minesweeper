package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.Position

class MineBoard(
    val board: Board,
    private val numberOfMines: Int
) {
    lateinit var mines: List<Cell>
        private set

    init {
        require(numberOfMines in (0..board.size)) { "number of mines must be within range of 0 ~ ${board.size}" }
        build()
    }

    private fun build() {
        val mineIndices = board.size.toShuffledMineIndices(numberOfMines) // TODO - 랜덤 값을 제공하는 클래스로 분리 & 관련 테스트 추가
        mines = List(board.size) {
            val x = it % board.width
            val y = it / board.width
            if (it in mineIndices) {
                Mine(Position(x, y))
            } else {
                Empty(Position(x, y))
            }
        }
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int) = (0 until this).shuffled().subList(0, numberOfMines)
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
