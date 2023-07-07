package domain

class Board(width: Int, height: Int, val mines: Int) {
    val board = Array(width) { Array(height) { Cell() } }

    val width = board.size
    val height = board[0].size
}
