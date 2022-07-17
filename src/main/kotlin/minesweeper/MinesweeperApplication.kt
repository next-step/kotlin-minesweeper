package minesweeper

import minesweeper.dto.MineBoardCreateDto
import minesweeper.dto.MineBoardDto
import minesweeper.model.MineBoard
import minesweeper.view.InputView
import minesweeper.view.ResultView

object MinesweeperApplication {
    fun run(inputView: InputView, resultView: ResultView) {
        inputView.printHeightInputMessage()
        val height = inputView.inputHeight()

        inputView.printWidthInputMessage()
        val width = inputView.inputWidth()

        inputView.printMineCountInputMessage()
        val mineCount = inputView.inputMineCount()

        val mineBoard = MineBoard.of(MineBoardCreateDto(width = width, height = height, mineCount = mineCount))
        resultView.printMinesweeperGameStartMessage()

        while (mineBoard.isOngoing) {
            val openTargetCellPosition = inputView.inputOpenCellPosition()
            mineBoard.openAtPositionAndSurroundingNonMineCells(openTargetCellPosition)
            resultView.printMineBoard(MineBoardDto.from(mineBoard))
        }

        resultView.printMinesweeperGameStatus(mineBoard.gameStatus)
    }
}
