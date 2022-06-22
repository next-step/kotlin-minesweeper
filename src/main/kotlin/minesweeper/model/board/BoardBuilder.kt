package minesweeper.model.board

fun interface BoardBuilder {
    fun createNewBoard(): Board
}
