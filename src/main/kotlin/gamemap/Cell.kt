package gamemap

data class Cell(
    val isMine: Boolean = false,
    val adjacentMineCount: Int = 0,
    var state: CellState = CellState.Close
) {
    init {
        require(adjacentMineCount in 0..8) { "adjacent mine count should be in 0..8 $adjacentMineCount of $this" }
    }

    fun open() {
        check(state != CellState.Open) { "cannot open a open cell $this" }
        state = CellState.Open
    }
}
