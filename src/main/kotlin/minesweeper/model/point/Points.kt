package minesweeper.model.point

import minesweeper.model.vison.VisionStrategy
import minesweeper.view.render.MineRenderingStrategy

class Points(
    private val points: Map<Coordinate, TileType>,
    private val vision: Map<Coordinate, Vision> = mapOf(),
) {

    constructor(points: Map<Coordinate, TileType>, visionStrategy: VisionStrategy) : this(points, visionStrategy.toData())

    fun symbol(coordinate: Coordinate, strategy: MineRenderingStrategy): String {
        val tileType = points[coordinate] ?: TileType.NONE
        return strategy.symbol(Attribute(tileType, Vision.VEILED), coordinate)
    }

    fun attribute(coordinate: Coordinate): TileType {
        return points[coordinate] ?: TileType.NONE
    }

    fun countOfMine(): Int {
        return points.values
            .count { it == TileType.MINE }
    }
}
