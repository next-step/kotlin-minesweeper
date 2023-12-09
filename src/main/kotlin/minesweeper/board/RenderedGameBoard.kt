package minesweeper.board

@JvmInline
value class RenderedGameBoard(
    val board: Array<Array<Char>>
) {

    fun joinToString(rowDelimiter: String = "\n", colDelimiter: String = " ") =
        board.joinToString(rowDelimiter) { it.joinToString(colDelimiter) }

    operator fun get(row: Int) = board[row]
}
