package minesweeper.model.board

import minesweeper.app.GameStatus
import minesweeper.model.board.minedeploy.impl.EvenlyStrategy
import minesweeper.model.board.traversal.Bfs
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import minesweeper.model.vison.impl.VisionCoveredStrategy

class Board(
    private val mines: Map<Coordinate, Attribute>,
    private val vision: MutableSet<Coordinate> = mutableSetOf(),
    val limit: BoardLimit,
) {

    val minesCount: Int
        get() = mines.values.count { it == Attribute.MINE }

    constructor(
        mineCount: Int,
        limit: BoardLimit,
    ) : this(
        mines = EvenlyStrategy(mineCount).deployPoints(limit),
        vision = VisionCoveredStrategy.coordinates(limit).toMutableSet(),
        limit = limit,
    )

    fun adjacentMineCount(coordinate: Coordinate): Int {
        return Delta.deltas.asSequence()
            .filter { delta -> inRange(coordinate, delta) }
            .map { this.attribute(coordinate.moveTo(it)) }
            .count { it.isMine() }
    }

    private fun inRange(coordinate: Coordinate, delta: Delta): Boolean {
        return coordinate.movePossible(
            delta = delta,
            limit = limit
        )
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
        return GameStatus.RUNNING
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

    fun isGroundAttribute(coordinate: Coordinate): Boolean {
        return this.attribute(coordinate) == Attribute.GROUND
    } // 읽기전용 프로퍼티

    fun isAdjacentMineCountZero(coordinate: Coordinate): Boolean {
        return this.adjacentMineCount(coordinate) == 0
    }
}
