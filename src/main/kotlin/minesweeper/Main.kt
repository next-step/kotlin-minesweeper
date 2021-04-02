package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.GameState
import minesweeper.domain.NotFoundCellException
import minesweeper.view.InputView
import minesweeper.view.OutputView

private const val INVALID_CELL_POSITION_MESSAGE = "잘못된 위치입니다."

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val board = Board.createBoard(inputView.requestBoardSpec())
    outputView.renderStartMessage()

    var gameState = GameState.RUNNING
    while (gameState == GameState.RUNNING) {
        try {
            gameState = board.expose(inputView.requestPosition())
        } catch (e: NotFoundCellException) {
            outputView.renderMessage(INVALID_CELL_POSITION_MESSAGE)
            continue
        }
        outputView.renderBoard(board, gameState)
    }
}
