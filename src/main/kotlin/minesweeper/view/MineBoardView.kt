package minesweeper.view

import minesweeper.domain.MineBoard

class MineBoardView {

    fun printlnMineBoard(mineBoard: MineBoard) {
        mineBoard.buttons.forEach { rowButtons ->
            println(rowButtons.joinToString(BUTTON_SEPARATOR))
        }
    }

    companion object {
        private const val BUTTON_SEPARATOR: String = " "
    }
}
