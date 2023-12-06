package minesweeper.model.board

import minesweeper.app.GameStatus
import minesweeper.model.board.minedeploy.impl.EvenlyStrategy
import minesweeper.model.board.traversal.SearchEngine
import minesweeper.model.board.traversal.impl.SearchBfs
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.vison.impl.VisionCoveredStrategy

class Board(
    val mines: Mines,
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
        mines = Mines(EvenlyStrategy(mineCount).deployPoints(limit), limit),
        vision = Vision(VisionCoveredStrategy.coordinates(limit)),
        limit = limit,
    )

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
        val searchEngine: SearchEngine = SearchBfs(this.limit, this.mines)
        return searchEngine.traversal(coordinate)
    }

    private fun discoveredAllMines() {
        vision.exposeAll(mines.coordinates)
    }

    private fun isMineDeployed(coordinate: Coordinate): Boolean =
        mines.attribute(coordinate) == Attribute.MINE
}
