package minesweeper.model.board

import minesweeper.app.GameStatus
import minesweeper.model.board.minedeploy.impl.EvenlyStrategy
import minesweeper.model.board.traversal.Bfs
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta
import minesweeper.model.vison.impl.VisionCoveredStrategy

class Board(
    private val mines: Mines,
    private val vision: Vision = Vision(emptySet()),
    val limit: BoardLimit,
) {
    private val isWin: Boolean
        get() = mines.count == vision.coveredCount

    val minesCount: Int
        get() = mines.count

    constructor(
        mineCount: Int,
        limit: BoardLimit,
    ) : this(
        mines = Mines(EvenlyStrategy(mineCount).deployPoints(limit)),
        vision = Vision(VisionCoveredStrategy.coordinates(limit)),
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
        return when (mines.isDeployedCoordinate(coordinate)) {
            true -> Attribute.MINE
            false -> Attribute.GROUND
        }
    }

    fun isCovered(coordinate: Coordinate): Boolean {
        return vision.isCovered(coordinate)
    }

    fun tryOpen(coordinate: Coordinate): GameStatus {
        if (isMineDeployed(coordinate)) {
            discoveredAllMines()
            return GameStatus.LOSE
        }
        if (isWin) {
            return GameStatus.WIN
        }
        discoveredAdjacentGround(coordinate)
        return GameStatus.RUNNING
    }

    private fun discoveredAdjacentGround(coordinate: Coordinate) {
        val coordinates: Set<Coordinate> = adjacentGroundCoordinates(coordinate)
        vision.exposeAll(coordinates)
    }

    private fun adjacentGroundCoordinates(coordinate: Coordinate): Set<Coordinate> {
        val bfs = Bfs(this)
        return bfs.traversal(coordinate)
    }

    private fun discoveredAllMines() {
        vision.exposeAll(mines.coordinates)
    }

    private fun isMineDeployed(coordinate: Coordinate): Boolean =
        this.attribute(coordinate) == Attribute.MINE

    fun isGround(coordinate: Coordinate): Boolean =
        this.attribute(coordinate) == Attribute.GROUND

    fun isAdjacentMineCountZero(coordinate: Coordinate): Boolean =
        this.adjacentMineCount(coordinate) == 0
}
