package minesweeper.domain

import minesweeper.domain.finder.AdjacentPoints

class MineBoard(
    val height: Int,
    val width: Int,
    val lines: Lines
) {

    init {
        lines.flatten().forEach(::updateSymbol)
    }

    private fun updateSymbol(minePoint: MinePoint) =
        getAdjacentPoints(minePoint)
            .filter { it.symbol == SymbolType.MINE }
            .forEach { _ -> minePoint.updateNextSymbol() }

    private fun findPoint(x: Int, y: Int): MinePoint? =
        when {
            x in (0 until width) && y in (0 until height) -> lines[x][y]
            else -> null
        }

    private fun getAdjacentPoints(point: MinePoint): List<MinePoint> {
        val foundPoint = findPoint(x = point.x, y = point.y)
        return if (foundPoint != null) {
            getAdjacentPointsOf(foundPoint)
        } else {
            emptyList()
        }
    }

    private fun getAdjacentPointsOf(point: MinePoint): List<MinePoint> =
        AdjacentPoints.values().mapNotNull {
            val movingPoint = it.movePoint(point)
            findPoint(x = movingPoint.x, y = movingPoint.y)
        }
}
