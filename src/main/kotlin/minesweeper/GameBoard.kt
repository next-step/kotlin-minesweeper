package minesweeper

data class GameBoard(
    val height: Height,
    val width: Width
) {

    fun toBooleanBoard(): Array<Array<Boolean>> =
        Array(height.value) { Array(width.value) { false } }
}
