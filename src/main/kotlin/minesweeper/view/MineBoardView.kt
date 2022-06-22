package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.rem

object MineBoardView {

    fun printStartOfGame(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        printMineBoard(mineBoard)
    }

    private fun printMineBoard(mineBoard: MineBoard) {
        mineBoard.cells.forEachIndexed { index, cell ->
            if (index % mineBoard.width == 0) {
                print("\n${cell.shape()} ")
            } else {
                print("${cell.shape()} ")
            }
        }
    }

    private fun Cell.shape() =
        when (this) {
            is Mine -> "*"
            is Empty -> this.numberOfNearbyMines
        }
}
