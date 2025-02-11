package application

import cell.CellBoard
import cell.Count
import cell.GameResult
import cell.Length
import view.InputView
import view.OutputView
import view.format

class MineSweeper {
    fun execute() {
        val (height, width, mineCount) = init()
        val board = CellBoard.of(height, width, mineCount)
        OutputView.printGameStart()
        gameLoop(board)
    }

    private fun init(): Request {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()

        return Request(Length(height), Length(width), Count(mineCount))
    }

    private tailrec fun gameLoop(board: CellBoard) {
        val coordinate = InputView.inputCoordinate()
        when (board.handleInput(coordinate)) {
            is GameResult.LOSE -> {
                OutputView.printLoseGame()
            }
            is GameResult.CONTINUE -> {
                renderBoard(board)
                gameLoop(board)
            }
        }
    }

    private fun renderBoard(board: CellBoard) {
        val formattedBoard = format(board)
        OutputView.printBoard(formattedBoard)
    }

    private data class Request(
        val height: Length,
        val width: Length,
        val mineCount: Count,
    )
}
