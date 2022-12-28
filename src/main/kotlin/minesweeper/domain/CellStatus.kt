package minesweeper.domain

enum class CellStatus(
    val description: String
) {
    EMPTY("C"),
    MINE("*")
}
