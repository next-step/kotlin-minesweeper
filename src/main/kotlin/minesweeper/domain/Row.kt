package minesweeper.domain

import minesweeper.domain.point.Point
import minesweeper.domain.point.SymbolPoint

class Row(private val columns: List<SymbolPoint>) : List<SymbolPoint> by columns {

    val realSize: Int = columns.size - BOUNDARY_SIZE

    companion object {
        private const val BOUNDARY_SIZE = 2

        fun createBoundary(size: Int, height: Int = 0): Row =
            List(size + BOUNDARY_SIZE) {
                SymbolPoint.createBoundaryPoint(Point(x = it, y = height))
            }.let(::Row)
    }
}
