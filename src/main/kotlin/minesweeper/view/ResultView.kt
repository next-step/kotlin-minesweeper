package minesweeper.view

import minesweeper.domain.MinesweeperBoard
import minesweeper.domain.MinesweeperGame

object ResultView {

    fun showMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        val symbolOfBoard = StringBuilder("\n지뢰찾기 게임 시작\n")
        minesweeperBoard.minesweeperBoard.forEach { it ->
            it.forEach {
                symbolOfBoard.append(" ${it.showSymbol()} ")
            }
            symbolOfBoard.append("\n")
        }
        println(symbolOfBoard)
    }

    fun showResult(minesweeperGame: MinesweeperGame) {
        println(minesweeperGame.playState.name)

        showMinesweeperBoard(minesweeperGame.minesweeperBoard)
    }

    fun showErrorMessage(message: String) {
        println(message)
    }
}
