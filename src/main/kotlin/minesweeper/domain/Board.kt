package minesweeper.domain

class Board private constructor(
    private val rows: Rows,
) {

    fun isMineCell(x: Int, y: Int): Boolean {
        return rows[x][y].hasMine()
    }

    override fun toString(): String {
        return rows.toString()
    }

    companion object {
        fun create(height: Int, width: Int, minesCoordinates: Coordinates): Board {
            val rows = Rows.create(height, width)

            minesCoordinates.forEach { coordinate ->
                val cell = rows[coordinate.x][coordinate.y]
                cell.plantMine()
            }

            return Board(rows)
        }
    }
}
