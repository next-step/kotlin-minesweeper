package mine

class Board(
    val width: Width,
    val height: Height
) {
    // val width: Width
    // val height: Height

    companion object {
        private const val MINX_MINE_SIZE = 0
        fun createBoard(
            width: Int,
            height: Int,
            mine: Int = MINX_MINE_SIZE
        ): Board {
            require(mine >= MINX_MINE_SIZE)

            return Board(Width(width), Height(height))
        }
    }
}
