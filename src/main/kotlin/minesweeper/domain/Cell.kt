package minesweeper.domain

class Cell private constructor(
    private val coordinate: Coordinate,
) {
    private var state: CellState = EmptyState

    fun plantMine() {
        state = MineState
    }

    fun hasMine(): Boolean {
        return state == MineState
    }

    override fun toString(): String {
        return when (hasMine()) {
            true -> MINE_CELL
            false -> EMPTY_CELL
        }
    }

    companion object {
        private const val MINE_CELL: String = "*"
        private const val EMPTY_CELL: String = "C"

        fun of(x: Int, y: Int): Cell {
            return Cell(Coordinate.of(x, y))
        }
    }
}
