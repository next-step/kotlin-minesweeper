package minesweeper.model.point

import minesweeper.view.reder.MineRenderingStrategy

class Points(
    private val points: Map<Coordinate, Attribute>
) {
    fun symbol(coordinate: Coordinate, strategy: MineRenderingStrategy): String {
        val attribute = points[coordinate] ?: Attribute.NONE
        return strategy.symbol(attribute)
    }

    fun countOfMine(): Int {
        return points.values
            .count { it == Attribute.MINE }
    }
}
