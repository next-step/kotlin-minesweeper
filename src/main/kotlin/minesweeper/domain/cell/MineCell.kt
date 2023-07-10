package minesweeper.domain.cell

import minesweeper.domain.point.Point

class MineCell(point: Point, override val count: Int = 0) : Cell(point) {

    override fun increase(): Cell = MineCell(point, count + 1)
}
