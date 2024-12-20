package application

import cell.CellBoard
import view.InputView
import view.OutputView

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

        return Request(height, width, mineCount)
    }

    private fun renderBoard(board: CellBoard) {
        val formattedBoard = format(board)
        OutputView.printGameStart(formattedBoard)
    }

    private data class Request(
        val height: Int,
        val width: Int,
        val mineCount: Int,
    )
}
