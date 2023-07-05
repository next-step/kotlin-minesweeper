package minesweeper.domain

class Board private constructor(
    private val rows: Rows,
) {

    override fun toString(): String {
        return rows.toString()
    }

    companion object {
        fun create(height: Int, width: Int, minesCoordinates: Coordinates): Board {
            val rows = Rows.create(height, width)

            minesCoordinates.forEach { coordinate ->
                val cell = rows[coordinate.y][coordinate.x]
                cell.plantMine()
            }

            return Board(rows)
        }
    }
}
