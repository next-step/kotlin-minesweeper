package minesweeper.view

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.EmptyPosition
import minesweeper.domain.position.MinePosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.MineSweeperPositions

class ResultView {

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printBoard(board: MineSweeperBoard) {
        board.rows().forEach { positions ->
            printPositions(positions)
        }
    }

    private fun printPositions(positions: MineSweeperPositions) {
        val positionsShape = positions.joinToString(separator = " ") {
            getPositionShape(it)
        }
        println(positionsShape)
    }

    private fun getPositionShape(it: MineSweeperPosition) = when (it) {
        is MinePosition -> MINE
        is EmptyPosition -> it.calculateAroundMineQuantity().toString()
    }

    companion object {
        private const val MINE = "*"
        private const val EMPTY = "C"
        private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    }
}
