package minesweeper.view

import minesweeper.domain.MinesweeperBoard

object ResultView {

    fun showMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        val symbolOfBoard = StringBuilder("\n지뢰찾기 게임 시작\n")
        minesweeperBoard.minesweeperBoard.forEach { it ->
            run {
                it.forEach { symbolOfBoard.append(" ${it.symbol} ") }
                symbolOfBoard.append("\n")
            }
        }
        println(symbolOfBoard)
    }
}
