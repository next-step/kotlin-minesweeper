package minesweeper.domain

class Cells(val cells: List<Cell>) {

    companion object {
        fun of(positions: List<Position>, minePositions: List<Position>): Cells {
            return positions.map { position ->
                Cell.of(position, minePositions.contains(position))
            }.run { Cells(this) }
        }
    }
}
