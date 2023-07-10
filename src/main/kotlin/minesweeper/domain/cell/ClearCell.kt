package minesweeper.domain.cell

import minesweeper.domain.point.Point

class ClearCell(point: Point, override val count: Int = 0) : Cell(point) {

    override fun increase(): Cell = HazardCell(point, 1)
}
