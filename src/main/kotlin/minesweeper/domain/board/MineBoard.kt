package minesweeper.domain.board

import minesweeper.domain.MineGenerator
import minesweeper.domain.Mines
import minesweeper.domain.component.Component
import minesweeper.domain.component.Components
import minesweeper.domain.component.DefaultComponent
import minesweeper.domain.coordinate.BaseCoordinateSystem
import minesweeper.domain.coordinate.MineCoordinateSystemDecorator
import minesweeper.domain.coordinate.MineCountingCoordinateSystemDecorator

class MineBoard(
    height: Int,
    width: Int,
    mineCount: Int,
    mineGenerator: MineGenerator
) : Components {
    private val coordinateSystem: MineCountingCoordinateSystemDecorator
    private val mineList: Mines
        get() = coordinateSystem.mines

    val height: Int
        get() = coordinateSystem.height
    val width: Int
        get() = coordinateSystem.width

    init {
        val baseCoordinateSystem = BaseCoordinateSystem(height = height, width = width)
        val mineCoordinateSystemDecorator = MineCoordinateSystemDecorator(baseCoordinateSystem, mineGenerator, mineCount)
        coordinateSystem = MineCountingCoordinateSystemDecorator(mineCoordinateSystemDecorator)
    }

    override fun components(): List<Component> {
        return this.coordinateSystem.coordinate.map {
            val isMine = mineList.positionList.contains(it)
            val countNumber = coordinateSystem.mineCountNumbers.getMineCountNumber(it)
            DefaultComponent(position = it, isMine = isMine, nearMineCount = countNumber)
        }.sortedWith(
            compareBy({ it.position.x }, { it.position.y })
        )
    }
}
