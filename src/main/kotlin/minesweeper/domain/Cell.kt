package minesweeper.domain

class Cell private constructor(
    val coordinate: Coordinate,
) {
    private var state: CellState = EmptyState

    fun plantMine() {
        state = MineState
    }

    fun hasMine(): Boolean {
        return state == MineState
    }

    companion object {
        fun of(x: Int, y: Int): Cell {
            return Cell(Coordinate.of(x, y))
        }
    }
}
