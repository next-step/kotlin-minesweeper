package minesweeper

class GameBoard(val height: Int, val width: Int) {
    private var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
