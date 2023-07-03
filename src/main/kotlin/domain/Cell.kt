package domain

data class Cell(
    val hasMine: Boolean,
    private var state: CellState = Closed,
) {
    fun open(aroundMineCount: AroundMineCount) {
        state = state.open(aroundMineCount)
    }

    fun isClosed(): Boolean {
        return state is Closed
    }

    fun aroundMineCount(): AroundMineCount = state.aroundMineCount()
}
