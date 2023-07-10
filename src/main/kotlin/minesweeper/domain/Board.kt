package minesweeper.domain

import minesweeper.error.MineSweeperErrorMessage.OUTSIDE_THE_BOUNDS_OF_THE_BOARD

class Board private constructor(
    val rows: Rows,
    private val minesCoordinates: Coordinates,
) {
    private val height: Int = rows.size
    private val width: Int = rows.first().size

    fun isMineCell(x: Int, y: Int): Boolean {
        return rows[x][y].hasMine()
    }

    fun countMinesNearby(cell: Cell): Int {
        val cellCoordinate = cell.coordinate

        require(cellCoordinate.checkWithinBounds(height, width)) { OUTSIDE_THE_BOUNDS_OF_THE_BOARD }

        var count = 0

        for (direction in Direction.values()) {
            val neighborX = cellCoordinate.x + direction.xMove
            val neighborY = cellCoordinate.y + direction.yMove

            if (!cellCoordinate.checkWithinBounds(height, width)) {
                continue
            }

            if (minesCoordinates.contains(Coordinate.of(neighborX, neighborY))) {
                count++
            }
        }

        return count
    }

    companion object {
        fun create(height: Int, width: Int, minesCoordinates: Coordinates): Board {
            val rows = Rows.create(height, width)

            minesCoordinates.forEach { coordinate ->
                val cell = rows[coordinate.y][coordinate.x]
                cell.plantMine()
            }

            return Board(rows, minesCoordinates)
        }
    }
}
