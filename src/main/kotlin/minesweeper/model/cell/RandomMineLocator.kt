package minesweeper.model.cell

import minesweeper.model.board.Board.Companion.maxMineCountInRandomBoard
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomMineLocator(private val area: Area, mineCount: Int) : MineLocator {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard)
    private var mineCoordinateMap: Map<Coordinate, Boolean>? = null

    override fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate): Boolean =
        mineCoordinate(firstClickCoordinate)[coordinate] ?: false

    private fun mineCoordinate(forceSafeCellCoordinate: Coordinate): Map<Coordinate, Boolean> {
        return mineCoordinateMap
            ?: randomMineCoordinates(forceSafeCellCoordinate).also { mineCoordinateMap = it }
    }

    private fun randomMineCoordinates(forceSafeCellCoordinate: Coordinate): Map<Coordinate, Boolean> {
        val mineCoordinates = area.shuffled()
            .filter { it != forceSafeCellCoordinate }
            .take(mineCount)
        return area.associateWith { it in mineCoordinates }
    }
}
