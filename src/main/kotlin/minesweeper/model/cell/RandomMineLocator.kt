package minesweeper.model.cell

import minesweeper.model.board.Board.Companion.maxMineCountInRandomBoard
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomMineLocator(private val area: Area, mineCount: Int) : MineLocator {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard)
    private var mineCoordinate: List<Coordinate>? = null

    override fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
        coordinate in mineCoordinate(firstClickCoordinate)

    private fun mineCoordinate(forceSafeCellCoordinate: Coordinate): List<Coordinate> {
        return mineCoordinate
            ?: randomMineCoordinates(forceSafeCellCoordinate).also { mineCoordinate = it }
    }

    private fun randomMineCoordinates(forceSafeCellCoordinate: Coordinate) = area.shuffled()
        .filter { it != forceSafeCellCoordinate }
        .take(mineCount)
}
