package minesweeper

data class GameBoard(
    val height: Int,
    val width: Int
) {
    constructor(height: String, width: String) : this(height.toInt(), width.toInt())
}
