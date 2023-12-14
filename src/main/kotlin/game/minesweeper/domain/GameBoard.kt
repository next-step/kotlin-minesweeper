package game.minesweeper.domain

class GameBoard(height: Int, width: Int) {
    private val board = Array(height) { Array(width) { "C" } }

    fun startGame(minesNumber: Int) {
        MineGenerator.create(board, minesNumber)
    }

    override fun toString(): String {
        return board.joinToString("\n") { row ->
            row.joinToString(" ")
        }
    }
}
