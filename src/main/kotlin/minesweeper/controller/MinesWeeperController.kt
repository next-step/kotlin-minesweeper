package minesweeper.controller

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

    }

    private fun getGameBoardRequest(): GameBoardRequest {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val minesNumber = inputView.getMinesNumber()
        return GameBoardRequest(height = height, width = width, minesNumber = minesNumber)
    }

    private fun generateGameBoard(gameBoardRequest: GameBoardRequest) {

    }
}
