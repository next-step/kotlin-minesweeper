package minesweeper.domain.cell

import minesweeper.domain.point.Point

class HazardCell(point: Point, override val count: Int) : Cell(point) {
    override fun increase(): Cell = HazardCell(point, count + 1)
}
