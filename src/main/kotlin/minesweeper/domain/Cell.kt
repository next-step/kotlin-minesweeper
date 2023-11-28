package minesweeper.domain

data class Cell(
    val position: Position,
    val state: CellState
)

fun Cell.getStateSymbol(): String {
    return this.state.symbol
}
