package minesweeper.model.board

import minesweeper.app.GameStatus
import minesweeper.model.board.impl.EvenlyStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import minesweeper.view.render.MineRenderingStrategy

class Board(
    private val mines: Map<Coordinate, Attribute>,
    private val covered: Set<Coordinate> = emptySet(),
    val limit: BoardLimit,
) {

    constructor(
        mineCount: Int,
        limit: BoardLimit,
    ) : this(
        mines = EvenlyStrategy(mineCount).deployPoints(limit),
        limit = limit
    )

    // constructor(points: Map<Coordinate, Attribute>, visionStrategy: VisionStrategy) : this(points, visionStrategy.toData())

    fun adjacentMineCount(coordinate: Coordinate): Int {
        return this.adjacentPointTraversal(coordinate)
            .asSequence()
            .map { this.attribute(it) }
            .count { it == Attribute.MINE }
    }

    private fun adjacentPointTraversal(coordinate: Coordinate): List<Coordinate> {
        return Delta.deltas.asSequence()
            .filter { delta -> inRange(coordinate, delta) }
            .map { coordinate.moveTo(it) }
            .toList()
    }

    private fun inRange(coordinate: Coordinate, delta: Delta): Boolean {
        return coordinate.movePossible(
            delta = delta,
            limit = limit
        )
    }

    fun symbol(coordinate: Coordinate, strategy: MineRenderingStrategy): String {
        val attribute = mines[coordinate] ?: Attribute.NONE
        return strategy.symbolOf(this, coordinate)
    }

    fun attribute(coordinate: Coordinate): Attribute {
        return mines[coordinate] ?: Attribute.NONE
    }

    fun isCovered(coordinate: Coordinate): Boolean {
        return this.covered.contains(coordinate)
    }

    fun tryOpen(coordinate: Coordinate): GameStatus {
        TODO("Not yet implemented")
    }

    fun minesCount(): Int {
        return mines.values.count { it == Attribute.MINE }
    }
}
