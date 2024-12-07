package minesweeper

data class Cell(
    val height: Height,
    val width: Width,
    var isMine: Boolean = false,
) {
    fun createMineCell(): Cell {
        return Cell(height, width, true)
    }
}
