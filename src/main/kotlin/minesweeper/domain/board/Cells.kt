package minesweeper.domain.board

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells =
            positions.map {
                if (minePositions.contains(it)) {
                    Cell.of(it, Mine())
                } else {
                    Cell.of(it)
                }
            }.run {
                Cells(this)
            }
    }
}
