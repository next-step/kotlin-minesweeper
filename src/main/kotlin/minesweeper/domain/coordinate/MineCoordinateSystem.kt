package minesweeper.domain.coordinate

import minesweeper.domain.MineGenerator
import minesweeper.domain.Mines

sealed interface MineCoordinateSystem : CoordinateSystem {
    val mines: Mines
}

class MineCoordinateSystemDecorator(
    private val baseCoordinateSystem: BaseCoordinateSystem,
    mineGenerator: MineGenerator,
    mineCount: Int
) : MineCoordinateSystem, CoordinateSystem by baseCoordinateSystem {
    override val mines: Mines

    init {
        require(mineCount <= height * width) { "좌표의 개수보다 지뢰의 개수가 많을 수 없다" }

        val mutableSet = buildSet {
            while (this.size != mineCount) {
                val mine = mineGenerator.generate(height, width)
                this.add(mine)
            }
        }

        mines = Mines(mineList = mutableSet.toList())
    }
}
