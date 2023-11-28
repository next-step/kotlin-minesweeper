package minesweeper.model.board

import minesweeper.model.board.impl.EvenlyStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import minesweeper.model.point.Delta.Companion.deltas
import minesweeper.model.point.Points

class Board(
    val points: Points,
    val boardSize: BoardSize,
) {

    constructor(
        mineCount: Int,
        verticalSize: Int,
        horizontalSize: Int,
    ) : this(
        points = EvenlyStrategy(mineCount).deployPoints(verticalSize, horizontalSize),
        verticalSize = verticalSize,
        horizontalSize = horizontalSize
    )

    fun minesCount(): Int {
        return points.countOfMine()
    }

    fun adjacentMineCount(coordinate: Coordinate): Int {
        return this.adjacentPointTraversal(coordinate)
            .asSequence()
            .map { points.attribute(it) }
            .count { it == Attribute.MINE }
    }

    private fun adjacentPointTraversal(coordinate: Coordinate): List<Coordinate> {
        return deltas.asSequence()
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
}
