package minesweeper.domain.coordinate

import minesweeper.domain.Mine
import minesweeper.domain.MineGenerator

class MineCoordinateSystemDecorator(
    private val coordinateSystem: CoordinateSystem,
    mineGenerator: MineGenerator,
    mineCount: Int
) : CoordinateSystem by coordinateSystem {
    val mineList: List<Mine>

    init {
        val mutableSet = mutableSetOf<Mine>()
        while (mutableSet.size != mineCount) {
            val mine = mineGenerator.generate(height, width)
            mutableSet.add(mine)
        }
        mineList = mutableSet.toList()
    }
}
