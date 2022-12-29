package minesweeper.view

import minesweeper.domain.MineSweeperGame
import minesweeper.domain.button.Button
import minesweeper.domain.button.ButtonGraph
import minesweeper.domain.button.Buttons
import minesweeper.domain.button.Mine
import minesweeper.domain.button.PushableButton

class MineBoardView {

    fun printlnMineBoard(mineSweeperGame: MineSweeperGame) {
        println(mineSweeperGame.toContentString())
    }

    private fun MineSweeperGame.toContentString(): String =
        buttonGraph.toContentString()

    private fun ButtonGraph.toContentString(): String =
        rowButtons().joinToString(ROW_BUTTONS_SEPARATOR) { it.toContentString() }

    private fun Buttons.toContentString(): String =
        sortedBy { it.position }
            .joinToString(BUTTON_SEPARATOR) {
                it.toContentString()
            }

    private fun Button.toContentString(): String = when (this) {
        is Mine -> MINE
        is PushableButton -> PUSHABLE_BUTTON
    }

    companion object {
        private const val BUTTON_SEPARATOR: String = " "
        private const val ROW_BUTTONS_SEPARATOR: String = "\n"
        private const val MINE = "*"
        private const val PUSHABLE_BUTTON = "C"
    }
}
