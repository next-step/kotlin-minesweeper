package minesweeper

import minesweeper.model.GameResult
import minesweeper.model.MineBoard
import minesweeper.model.MineBoardCreateDto
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

        var gameResult = GameResult.from(mineBoard)
        while (gameResult.isOngoing) {
            val openTargetCellPosition = inputView.inputOpenCellPosition()
            val openTargetCell = mineBoard[openTargetCellPosition.x.position][openTargetCellPosition.y.position]
            openTargetCell.openMeAndSurroundingNonMineCells(mineBoard)

            gameResult = GameResult.from(mineBoard)
            resultView.printMineBoard(gameResult)
        }

        resultView.printMinesweeperGameResult(gameResult)
    }
}
