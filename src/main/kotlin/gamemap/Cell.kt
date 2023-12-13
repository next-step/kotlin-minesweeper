package gamemap

data class Cell internal constructor(
    val isMine: Boolean = false,
    val adjacentMineCount: Int = 0,
    private var state: CellState = CellState.Close
) {
    init {
        require(adjacentMineCount in 0..8) { "adjacent mine count should be in 0..8 $adjacentMineCount of $this" }
    }

    fun open() {
        state = state.open()
    }

    fun isOpen() = state == CellState.Open

    fun isClose() = state == CellState.Close
}
