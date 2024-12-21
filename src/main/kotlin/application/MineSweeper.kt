package application

import cell.CellBoard
import cell.Count
import cell.Length
import view.InputView
import view.OutputView
import view.format

class MineSweeper {
    fun execute() {
        val (height, width, mineCount) = init()

        val board = CellBoard.of(height, width, mineCount)

        renderBoard(board)
    }

    private fun init(): Request {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()

        return Request(Length(height), Length(width), Count(mineCount))
    }

    private fun renderBoard(board: CellBoard) {
        val formattedBoard = format(board)
        OutputView.printGameStart(formattedBoard)
    }

    private data class Request(
        val height: Length,
        val width: Length,
        val mineCount: Count,
    )
}
