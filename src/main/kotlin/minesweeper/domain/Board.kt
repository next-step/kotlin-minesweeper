package minesweeper.domain

import minesweeper.error.MineSweeperErrorMessage.OUTSIDE_THE_BOUNDS_OF_THE_BOARD

class Board private constructor(
    val rows: Rows,
) {
    private val height: Int = rows.size
    private val width: Int = rows.first().size

    fun isMineCell(x: Int, y: Int): Boolean {
        return rows.hasMine(x, y)
    }

    fun countMinesNearby(cell: Cell): Int {
        val cellCoordinate = cell.coordinate

        require(cellCoordinate.checkWithinBounds(height, width)) { OUTSIDE_THE_BOUNDS_OF_THE_BOARD }

        var count = 0

        for (direction in Direction.values()) {
            val neighborX = cellCoordinate.x + direction.xMove
            val neighborY = cellCoordinate.y + direction.yMove
            val neighborCoordinate = Coordinate.of(neighborX, neighborY)

            if (!cellCoordinate.checkWithinBounds(height, width)) {
                continue
            }

            if (!neighborCoordinate.checkWithinBounds(height, width)) {
                continue
            }

            if (isMineCell(neighborX, neighborY)) {
                count++
            }
        }

        return count
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
