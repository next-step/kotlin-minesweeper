package minesweeper.view

import minesweeper.domain.CellType
import minesweeper.domain.MinesweeperBoard

object ResultView {

    fun showMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        val symbolOfBoard = StringBuilder("\n지뢰찾기 게임 시작\n")
        minesweeperBoard.minesweeperBoard.forEach { it ->
            it.forEach {
                when (it.cellType) {
                    CellType.MINE -> symbolOfBoard.append(" ${it.cellType.symbol} ")
                    CellType.LOAD -> symbolOfBoard.append(" ${it.numberOfNeighboringMine} ")
                }
            }
            symbolOfBoard.append("\n")
        }
        println(symbolOfBoard)
    }
}
