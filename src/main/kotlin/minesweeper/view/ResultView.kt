package minesweeper.view

import minesweeper.domain.MinesweeperGame
import minesweeper.domain.PlayState

object ResultView {

    private const val START_NOTICE = "\n지뢰찾기 게임 시작\n"
    private const val FINISH_NOTICE = "\n종료 : "

    fun showMinesweeperBoard(minesweeperGame: MinesweeperGame) {
        val symbolOfBoard = StringBuilder()
        minesweeperGame.minesweeperBoard.minesweeperBoard.forEach { it ->
            it.forEach { symbolOfBoard.append(" ${it.showSymbol()} ") }
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
}
