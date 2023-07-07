package minesweeper.controller

import minesweeper.domain.GameBoardSquare
import minesweeper.domain.MinesWeeperGameBoard
import minesweeper.dto.GameBoardRequest
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class MinesWeeperController {
    private val inputView = InputView()
    private val resultView = ResultView()
    fun start() {
        val gameBoardRequest = getGameBoardRequest()
        val minesWeeperGameBoard = MinesWeeperGameBoard(gameBoardRequest)
        printMinesWeeperGame(minesWeeperGameBoard.getBoard())
    }

    private fun getGameBoardRequest(): GameBoardRequest {
        val height = getHeight()
        val width = getWidth()
        val minesNumber = getMinesNumber()
        return GameBoardRequest(height = height, width = width, minesNumber = minesNumber)
    }

    private fun getHeight(): Int {
        return inputView.getHeight()
    }

    private fun getWidth(): Int {
        return inputView.getWidth()
    }

    private fun getMinesNumber(): Int {
        return inputView.getMinesNumber()
    }

    private fun printMinesWeeperGame(gameBoard: List<List<GameBoardSquare>>) {
        resultView.printGameBoard(gameBoard)
    }
}
