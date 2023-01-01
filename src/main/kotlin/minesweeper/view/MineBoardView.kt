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

    companion object {
        const val BUTTON_SEPARATOR: String = " "
        const val ROW_BUTTONS_SEPARATOR: String = "\n"
        const val MINE = "*"
        const val PUSHABLE_BUTTON = "C"
    }
}

fun MineSweeperGame.toContentString(): String =
    buttonGraph.toContentString()

fun ButtonGraph.toContentString(): String =
    rowButtons().joinToString(MineBoardView.ROW_BUTTONS_SEPARATOR) { it.toContentString() }

fun Buttons.toContentString(): String =
    sortedBy { it.position }
        .joinToString(MineBoardView.BUTTON_SEPARATOR) {
            it.toContentString()
        }

fun Button.toContentString(): String = when (this) {
    is Mine -> MineBoardView.MINE
    is PushableButton -> this.aroundMineCount.toString()
}
