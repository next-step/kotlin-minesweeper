package minesweeper.view

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.EmptyPosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.MineSweeperPositions

class ResultView {

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }
    fun printBoard(board: MineSweeperBoard) {
        board.rows().forEach { mineSweeperPositions ->
            printPositions(positions = mineSweeperPositions)
        }
        println()
    }

    fun printLoseGameMessage() {
        println(LOSE_MESSAGE)
    }

    private fun printPositions(positions: MineSweeperPositions) {
        val positionsShape = positions.joinToString(separator = " ") {
            getPositionShape(position = it)
        }
        println(positionsShape)
    }

    private fun getPositionShape(position: MineSweeperPosition): String {
        if (position.isVisit() && position is EmptyPosition) {
            return position.calculateAroundMineQuantity().toString()
        }
        return NON_VISIT
    }

    fun printWinGameMessage() {
        println(WIN_MESSAGE)
    }

    companion object {
        private const val NON_VISIT = "C"
        private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
        private const val LOSE_MESSAGE = "Lose Game."
        private const val WIN_MESSAGE = "Win Game."
    }
}
