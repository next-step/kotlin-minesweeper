package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomCellBuilder(area: Area, private val mineCount: Int) : CellBuilder(area) {

    private var mineCoordinate: List<Coordinate>? = null
    override fun isMineCell(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
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
