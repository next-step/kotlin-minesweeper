package minesweeper.controller

import minesweeper.domain.BoardState
import minesweeper.domain.GameBoard
import minesweeper.domain.GameBoardSize
import minesweeper.domain.Position
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {

    fun execute() {
        val width = InputView.width()
        val height = InputView.height()
        val mineCount = InputView.mine()
        val gameBoard = GameBoard.of(GameBoardSize(width, height), mineCount)
        OutputView.printGameStart()
        playGame(gameBoard)
    }

    private fun playGame(gameBoard: GameBoard) {
        val (x, y) = InputView.click()
        when (gameBoard.click(Position(x, y))) {
            BoardState.BOMB -> OutputView.printLose()
            BoardState.WIN -> OutputView.printWin()
            BoardState.CONTINUE -> {
                OutputView.printGameBoard(gameBoard.cells)
                playGame(gameBoard)
            }
        }
    }
}
