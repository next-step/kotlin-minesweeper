package minesweeper

@JvmInline
value class RenderedGameBoard(
    private val board: Array<Array<Char>>
) {

    fun joinToString(rowDelimiter: String = "\n", colDelimiter: String = " ") =
        board.joinToString(rowDelimiter) { it.joinToString(colDelimiter) }
}
