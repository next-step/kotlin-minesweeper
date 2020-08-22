package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.MinesweeperGame
import minesweeper.domain.PlayState

object ResultView {

    private const val START_NOTICE = "\n지뢰찾기 게임 시작\n"
    private const val FINISH_NOTICE = "\n종료 : "
    private const val LOAD_SYMBOL = "\uD83C\uDF2B️️"
    private const val MINE_CHARACTER = "\uD83D\uDCA3"
    private const val LOAD_CHARACTER = "\uD83D\uDFE9"

    fun showMinesweeperBoard(minesweeperGame: MinesweeperGame) {
        val symbolOfBoard = StringBuilder()
        minesweeperGame.minesweeperBoard.minesweeperBoard.forEach { it ->
            it.forEach { symbolOfBoard.append(" ${getCellSymbol(it)} ") }
            symbolOfBoard.append("\n")
        }
        println(symbolOfBoard.addMessageForPlayState(minesweeperGame.playState))
    }

    private fun StringBuilder.addMessageForPlayState(playState: PlayState): StringBuilder {
        if (playState == PlayState.READY) this.insert(0, START_NOTICE)
        if (playState == PlayState.LOSE || playState == PlayState.WIN) this.append(FINISH_NOTICE + playState.name)
        return this
    }

    fun showErrorMessage(message: String) {
        println(message)
    }

    private fun getCellSymbol(cell: Cell): String {
        if (cell.isOpen) return getOpenCellSymbol(cell)
        return LOAD_SYMBOL
    }

    private fun getOpenCellSymbol(cell: Cell): String {
        if (cell.cellType != CellType.MINE && cell.numberOfNeighboringMine > 0) {
            return "${cell.numberOfNeighboringMine} "
        }
        if (cell.cellType == CellType.MINE) return MINE_CHARACTER
        return LOAD_CHARACTER
    }
}
