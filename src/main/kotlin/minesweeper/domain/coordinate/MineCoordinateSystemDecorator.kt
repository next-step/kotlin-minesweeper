package minesweeper.domain.coordinate

import minesweeper.domain.Mine
import minesweeper.domain.MineGenerator
import minesweeper.domain.Mines

class MineCoordinateSystemDecorator(
    private val coordinateSystem: CoordinateSystem,
    mineGenerator: MineGenerator,
    mineCount: Int
) : CoordinateSystem by coordinateSystem {
    val mines: Mines

    init {
        require(mineCount <= height * width) { "좌표의 개수보다 지뢰의 개수가 많을 수 없다" }
        val mutableSet = mutableSetOf<Mine>()
        while (mutableSet.size != mineCount) {
            val mine = mineGenerator.generate(height, width)
            mutableSet.add(mine)
        }
        mines = Mines(mineList = mutableSet.toList())
    }
}
