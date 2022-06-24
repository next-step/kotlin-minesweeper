package minesweeper.model.board

fun interface BoardGenerator {
    fun createBoard(): Board
}
