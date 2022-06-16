package minesweeper.domain

enum class CellType(val point: String) {
    MINE("*"),
    NON_MINE("C")
}
