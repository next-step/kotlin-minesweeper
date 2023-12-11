package gamemap

data class Cell(
    val isMine: Boolean = false,
    val adjacentMineCount: Int = 0,
    var state: CellState = CellState.Close
) {
    init {
        require(adjacentMineCount in 0..8) { "adjacent mine count should be in 0..8 $adjacentMineCount of $this" }
    }

    val displayValue = if (state == CellState.Close) CLOSE_DISPLAY_CHARACTER else adjacentMineCount.toString()

    fun open() {
        check(state != CellState.Open) { "cannot open a open cell $this" }
        state = CellState.Open
    }

    companion object {
        const val CLOSE_DISPLAY_CHARACTER = "C"
    }
}
