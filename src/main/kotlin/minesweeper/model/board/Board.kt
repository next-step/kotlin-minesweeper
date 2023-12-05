package minesweeper.model.board

import minesweeper.app.GameStatus
import minesweeper.model.board.impl.EvenlyStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import minesweeper.model.vison.impl.VisionCoveredStrategy
import minesweeper.view.render.MineRenderingStrategy

class Board(
    private val mines: Map<Coordinate, Attribute>,
    private val vision: MutableSet<Coordinate> = mutableSetOf(),
    val limit: BoardLimit,
) {

    constructor(
        mineCount: Int,
        limit: BoardLimit,
    ) : this(
        mines = EvenlyStrategy(mineCount).deployPoints(limit),
        vision = VisionCoveredStrategy.coordinates(limit).toMutableSet(),
        limit = limit,
    )

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
        return strategy.symbolOf(this, coordinate)
    }

    fun attribute(coordinate: Coordinate): Attribute {
        return mines[coordinate] ?: Attribute.GROUND
    }

    fun isCovered(coordinate: Coordinate): Boolean {
        return this.vision.contains(coordinate)
    }

    fun tryOpen(coordinate: Coordinate): GameStatus {
        if (isMineDeployed(coordinate)) {
            discoveredAllMines()
            return GameStatus.LOSE
        }
        if (isWin()) {
            return GameStatus.WIN
        }
        discoveredAdjacentGround(coordinate)
        return GameStatus.ALIVE
    }

    private fun discoveredAdjacentGround(coordinate: Coordinate) {
        val coordinates: Set<Coordinate> = adjacentGroundCoordinates(coordinate)
        discoverCoordinates(coordinates)
    }

    private fun adjacentGroundCoordinates(coordinate: Coordinate): Set<Coordinate> {
        val bfs = Bfs(this)
        return bfs.traversal(coordinate)
    }

    private fun discoveredAllMines() {
        discoverCoordinates(mines.keys)
    }

    private fun discoverCoordinates(coordinates: Set<Coordinate>) {
        vision.removeAll(coordinates)
    }

    private fun isWin(): Boolean {
        return mines.keys.size == vision.size
    }

    private fun isMineDeployed(coordinate: Coordinate) = mines.keys.contains(coordinate)

    fun minesCount(): Int {
        return mines.values.count { it == Attribute.MINE }
    }

    fun isGroundAttribute(coordinate: Coordinate): Boolean {
        return this.attribute(coordinate) == Attribute.GROUND
    }

    fun isAdjacentMineCountZero(coordinate: Coordinate): Boolean {
        return this.adjacentMineCount(coordinate) == 0
    }
}
