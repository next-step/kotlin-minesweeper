package minesweeper.domain

class Cell private constructor(
    val coordinate: Coordinate,
    val countMinesNearBy: Int = 0,
    initialState: CellState = EmptyState,
) {
    private var state: CellState = initialState

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

        fun of(x: Int, y: Int, countMinesNearBy: Int, state: CellState): Cell {
            return Cell(Coordinate.of(x, y), countMinesNearBy, state)
        }
    }
}
