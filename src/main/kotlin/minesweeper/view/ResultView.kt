package minesweeper.view

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.MineSweeperPositions

class ResultView {

    fun printBoard(board: MineSweeperBoard) {
        board.forEach { positions ->
            printPositions(positions)
        }
    }

    private fun printPositions(positions: MineSweeperPositions) {
        val positionsShape = positions.joinToString(separator = " ") {
            getPositionShape(it)
        }
        println(positionsShape)
    }

    private fun getPositionShape(it: MineSweeperPosition) = when (it.isMine()) {
        true -> MINE
        false -> EMPTY
    }

    companion object {
        private const val MINE = "*"
        private const val EMPTY = "C"
    }
}
