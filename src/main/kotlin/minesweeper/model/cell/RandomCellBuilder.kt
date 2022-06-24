package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomCellBuilder(area: Area, private val mineCount: Int) : CellBuilder(area) {

    private var mineCoordinate: List<Coordinate>? = null
    override fun isMineCell(coordinate: Coordinate, firstClickCoordinate: Coordinate): Boolean {
        val mineCoordinate = this.mineCoordinate ?: createRandomMineCoordinates(firstClickCoordinate)
        this.mineCoordinate = mineCoordinate
        return coordinate in mineCoordinate
    }

    private fun createRandomMineCoordinates(forceSafeCellCoordinate: Coordinate): List<Coordinate> =
        area.shuffled()
            .filter { it != forceSafeCellCoordinate }
            .take(mineCount)
}
