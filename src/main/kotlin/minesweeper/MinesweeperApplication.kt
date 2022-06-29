package minesweeper

import minesweeper.dto.GameResultDto
import minesweeper.dto.MineBoardCreateDto
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

        var gameResult = GameResultDto.from(mineBoard)
        while (gameResult.isOngoing) {
            val openTargetCellPosition = inputView.inputOpenCellPosition()
            mineBoard.openAndSurroundingNonMineCells(openTargetCellPosition)

            gameResult = GameResultDto.from(mineBoard)
            resultView.printMineBoard(gameResult)
        }

        resultView.printMinesweeperGameResult(gameResult)
    }
}
