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
        resultView.printGetHeight()
        return inputView.getOneNumber()
    }

    private fun getWidth(): Int {
        resultView.printGetWidth()
        return inputView.getOneNumber()
    }

    private fun getMinesNumber(): Int {
        resultView.printGetMinesNumber()
        return inputView.getOneNumber()
    }

    private fun printMinesWeeperGame(gameBoard: List<List<GameBoardSquare>>) {
        resultView.printGameBoard(gameBoard)
    }
}
