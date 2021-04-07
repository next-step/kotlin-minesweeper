package minesweeper.view

import minesweeper.MinesweeperCell

object OutputView {

    private fun MinesweeperCell.getValue() = when {
        this.isMine -> "*"
        else -> "C"
    }

    fun show(mineFramePanel: Array<Array<MinesweeperCell>>) {
        println("지뢰찾기 게임 시작")
        mineFramePanel.forEach {
            val joinToString = it.joinToString(separator = " ") { position -> position.getValue() }
            println(joinToString)
        }
    }
}
