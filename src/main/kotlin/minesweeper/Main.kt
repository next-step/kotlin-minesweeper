package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.GameState
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val board = Board.createBoard(inputView.requestBoardSpec())
    outputView.renderStartMessage()

    var gameState = GameState.RUNNING
    while (gameState == GameState.RUNNING) {
        gameState = board.expose(inputView.requestPosition())
        outputView.renderBoard(board, gameState)
    }
}
