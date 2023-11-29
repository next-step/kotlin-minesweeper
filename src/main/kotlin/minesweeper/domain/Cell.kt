package minesweeper.domain

data class Cell(
    val state: CellState
)

fun Cell.getStateSymbol(): String {
    return this.state.symbol
}
