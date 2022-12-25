package minesweeper.view

import minesweeper.domain.MineBoard
import minesweeper.domain.button.Button
import minesweeper.domain.button.Mine
import minesweeper.domain.button.NoMine

class MineBoardView {

    fun printlnMineBoard(mineBoard: MineBoard) {
        mineBoard.buttons.forEach { rowButtons ->
            println(
                rowButtons.joinToString(BUTTON_SEPARATOR) { button ->
                    button.toContentString()
                }
            )
        }
    }

    companion object {
        private const val BUTTON_SEPARATOR: String = " "
    }
}

fun Button.toContentString(): String = when (this) {
    is Mine -> "*"
    is NoMine -> "C"
}
