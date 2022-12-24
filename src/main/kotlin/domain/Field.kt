package domain

import domain.coord.Coordinate

class Field(
    val rows: List<Row>
) {
    companion object {
        fun create(height: Int, width: Int, mines: LocationOfMines): Field {
            val rows = (0 until height).map { y -> y.rows(width, mines) }

            return Field(rows)
        }

        private fun Int.rows(maxWidth: Int, mines: LocationOfMines) = Row(
            (0 until maxWidth).map { x ->
                Cell(Coordinate(this, x).toBlock(mines))
            }
        )
    }
}
