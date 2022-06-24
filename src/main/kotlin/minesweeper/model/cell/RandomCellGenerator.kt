package minesweeper.model.cell

import minesweeper.model.board.Board.Companion.maxMineCountInRandomBoard
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomCellGenerator(area: Area, mineCount: Int) : CellGenerator(area) {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard)

    private var mineCoordinate: List<Coordinate>? = null
    override fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
        coordinate in mineCoordinate(firstClickCoordinate)

    private fun mineCoordinate(forceSafeCellCoordinate: Coordinate): List<Coordinate> {
        val mineCoordinate = this.mineCoordinate ?: randomMineCoordinates(forceSafeCellCoordinate)
        this.mineCoordinate = mineCoordinate
        return mineCoordinate
    }

    private fun randomMineCoordinates(forceSafeCellCoordinate: Coordinate) = area.shuffled()
        .filter { it != forceSafeCellCoordinate }
        .take(mineCount)
}
