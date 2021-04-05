package minesweeper.ui

import minesweeper.domain.gameboard.MineBoard
import minesweeper.domain.gameboard.Row

object OutputView {

    fun drawBoard(mineBoard: MineBoard) {
        repeat(mineBoard.height) { heightIndex ->
            drawWidth(mineBoard.board, heightIndex, mineBoard.width)
            println()
        }
    }

    private fun drawWidth(mineBoard: Array<Row>, heightIndex: Int, width: Int) {
        repeat(width) { widthIndex ->
            val block = mineBoard[heightIndex].getColumnBlock(widthIndex)
            print(block.value)
        }
    }
}
