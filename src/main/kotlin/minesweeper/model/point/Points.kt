package minesweeper.model.point

import minesweeper.view.render.MineRenderingStrategy

class Points(
    private val points: Map<Coordinate, TileType>
) {

    private var _points: Map<Coordinate, Attribute> = TODO()

    fun symbol(coordinate: Coordinate, strategy: MineRenderingStrategy): String {
        val tileType = points[coordinate] ?: TileType.NONE
        return strategy.symbol(tileType, coordinate)
    }

    fun attribute(coordinate: Coordinate): TileType {
        return points[coordinate] ?: TileType.NONE
    }

    fun countOfMine(): Int {
        return points.values
            .count { it == TileType.MINE }
    }
}
