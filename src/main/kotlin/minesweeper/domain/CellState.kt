package minesweeper.domain

enum class CellState(val symbol: String = "") {
    MINE("*"),
    EMPTY("C");
}
