package minesweeper

import minesweeper.domain.MineBoardLength
import minesweeper.domain.MineCount
import minesweeper.domain.cell.Coordinate
import minesweeper.domain.cell.CoordinateValue

fun Coordinate(
    x: Int,
    y: Int,
): Coordinate = Coordinate(
    x = CoordinateValue(value = x),
    y = CoordinateValue(value = y)
)

object Coordinates {
    fun from(
        height: Int,
        width: Int,
    ): minesweeper.domain.cell.Coordinates = minesweeper.domain.cell.Coordinates.from(
        height = MineBoardLength(height),
        width = MineBoardLength(width)
    )
}

object MineBoard {
    fun create(
        height: Int,
        width: Int,
        mineCount: Int,
    ): minesweeper.domain.MineBoard = minesweeper.domain.MineBoard.create(
        height = MineBoardLength(height),
        width = MineBoardLength(width),
        mineCount = MineCount(mineCount)
    )
}
