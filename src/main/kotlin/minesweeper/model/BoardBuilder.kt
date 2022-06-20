package minesweeper.model

import minesweeper.model.board.Board

fun interface BoardBuilder {
    fun createNewBoard(): Board
}
