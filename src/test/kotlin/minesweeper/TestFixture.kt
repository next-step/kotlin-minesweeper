package minesweeper

import minesweeper.domain.Coordinate
import minesweeper.domain.CoordinateValue
import minesweeper.domain.DotStatus
import minesweeper.domain.Land
import minesweeper.dto.MineBoardLength
import minesweeper.dto.MineCount

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
    ): minesweeper.domain.Coordinates = minesweeper.domain.Coordinates.from(
        height = MineBoardLength(height),
        width = MineBoardLength(width)
    )
}

fun Land(
    mineCount: Int,
    status: DotStatus = DotStatus.HIDDEN,
): Land = Land(
    mineCount = MineCount(value = mineCount),
    status = status
)

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
