package minesweeper

data class GameBoard(
    val height: Height,
    val width: Width
) {

    fun toIntBoard(): Array<Array<Int>> =
        Array(height.value) { Array(width.value) { INIT_CELL } }

    companion object {
        private const val INIT_CELL = 0
    }
}
