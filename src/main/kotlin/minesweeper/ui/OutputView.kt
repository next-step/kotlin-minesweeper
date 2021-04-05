package minesweeper.ui

import minesweeper.domain.MineBoard
import minesweeper.domain.Row

object OutputView {

    fun drawBoard(mineBoard: MineBoard) {
        repeat(mineBoard.height) { heightIndex ->
            drawWidth(mineBoard.board, heightIndex, mineBoard.width)
            println()
        }
    }

    private fun drawWidth(mineBoard: Array<Row>, heightIndex: Int, width: Int) {
        repeat(width) { widthIndex ->
            print(mineBoard[heightIndex].getColumnValue(widthIndex))
        }
    }
}
