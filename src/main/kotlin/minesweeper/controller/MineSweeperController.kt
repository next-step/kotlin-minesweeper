package minesweeper.controller

import minesweeper.domain.board.BoardRange
import minesweeper.domain.game.MineSweeperGame
import minesweeper.domain.position.Position
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController(private val inputView: InputView, private val resultView: ResultView) {
    private val game: MineSweeperGame by lazy {
        initGame()
    }

    private fun initGame(): MineSweeperGame {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineQuantity = inputView.readMineQuantity()

        val boardRange = BoardRange(height = height, width = width)

        resultView.printGameStartMessage()
        return MineSweeperGame(boardRange = boardRange, mineQuantity = mineQuantity)
    }

    fun playGame() {
        game.playGame(
            positionGetter = this::readPosition,
            showExploredBoard = resultView::printBoard,
            showLoseGameMessage = resultView::printLoseGameMessage,
            showWinGameMessage = resultView::printWinGameMessage,
        )
    }

    private fun readPosition(): Position {
        val position = inputView.readPosition()
        val inputPosition = position.split(",").map { it.trim() }.map { it.toInt() }
        return Position(x = inputPosition[0], y = inputPosition[1])
    }
}
