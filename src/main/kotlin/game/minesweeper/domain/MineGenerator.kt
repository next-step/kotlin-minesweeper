package game.minesweeper.domain

object MineGenerator {
    fun create(board: Array<Array<String>>, minesNumber: Int) {
        val height = board.size
        val width = board[0].size
        var minesPlaced = 0

        while (minesPlaced < minesNumber) {
            val randomRow = (0 until height).random()
            val randomCol = (0 until width).random()

            if (board[randomRow][randomCol] != "*") {
                board[randomRow][randomCol] = "*"
                minesPlaced++
            }
        }
    }
}
