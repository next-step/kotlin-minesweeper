package minesweeper.domain

class Size(
    val row: Int,
    val col: Int
) {
    init {
        MapValidator.validate(row, col)
    }
}
