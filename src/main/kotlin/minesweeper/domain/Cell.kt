package minesweeper.domain

class Cell(private var state: CellState = CellState.ZERO) {
    val symbol: String
        get() = state.getSymbol()

    fun changeState(state: CellState) {
        this.state = state
    }
}
