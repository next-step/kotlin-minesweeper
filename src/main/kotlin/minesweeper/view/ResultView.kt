package minesweeper.view

import minesweeper.view.model.BoardView

object ResultView {

    fun printCurrentMinesweeperBoard(boardView: BoardView) {
        boardView.forEach { _, rows ->
            println(message = rows.toString())
        }
    }
}
