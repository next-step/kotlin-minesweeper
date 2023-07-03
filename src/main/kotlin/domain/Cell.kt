package domain

data class Cell(
    val hasMine: Boolean,
    private var state: CellState = Closed,
) {
    fun open(aroundMineCount: Int) {
        state = state.open(aroundMineCount)
    }

    fun isClosed(): Boolean {
        return state is Closed
    }

    fun aroundMineCount(): Int = state.aroundMineCount()
}
