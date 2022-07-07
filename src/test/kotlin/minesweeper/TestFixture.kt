package minesweeper

import minesweeper.domain.MineBoardLength
import minesweeper.domain.MineCount
import minesweeper.domain.cell.Coordinate
import minesweeper.domain.cell.CoordinateValue
import minesweeper.domain.cell.DotStatus
import minesweeper.domain.cell.Land

fun Coordinate(
    x: Int,
    y: Int,
): Coordinate = Coordinate(
    x = CoordinateValue(value = x),
    y = CoordinateValue(value = y)
)

fun Land(
    mineCount: Int,
    status: DotStatus = DotStatus.HIDDEN,
): Land = Land(
    mineCount = MineCount(value = mineCount),
    status = status
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
