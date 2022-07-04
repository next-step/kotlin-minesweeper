package view

import domain.Board
import domain.Cell
import domain.GameInfo
import domain.Square

object OutputView {
    private const val mineSymbol = "*"
    private const val noneMineSymbol = "C"

    fun printMinesweeperBoard(board: Board, gameInfo: GameInfo) {
        (1..gameInfo.vertical.number).forEach() { yIdx ->
            (1..gameInfo.horizontal.number).forEach() { xIdx ->
                val cell = board.cells.getCell(xIdx, yIdx)
                print(getSquareSymbol(cell) + " ")
            }
            println()
        }
    }

    private fun getSquareSymbol(cell: Cell): String {
        if (cell.square is Square.Mine) return mineSymbol

        return noneMineSymbol
    }
}
