package minesweeper.domain.board

import minesweeper.domain.MineGenerator
import minesweeper.domain.Mines
import minesweeper.domain.component.Component
import minesweeper.domain.component.Components
import minesweeper.domain.component.DefaultComponent
import minesweeper.domain.coordinate.BaseCoordinateSystem
import minesweeper.domain.coordinate.MineCoordinateSystemDecorator

class MineBoard(
    height: Int,
    width: Int,
    mineCount: Int,
    mineGenerator: MineGenerator
) : Board, Components {
    private val coordinateSystem: MineCoordinateSystemDecorator
    private val mineList: Mines
        get() = coordinateSystem.mines

    override val height: Int
        get() = coordinateSystem.height
    override val width: Int
        get() = coordinateSystem.width

    init {
        val baseCoordinateSystem = BaseCoordinateSystem(height = height, width = width)
        coordinateSystem = MineCoordinateSystemDecorator(coordinateSystem = baseCoordinateSystem, mineGenerator, mineCount)
    }

    override fun components(): List<Component> {
        return this.coordinateSystem.coordinate.map {
            val isMine = mineList.positionList.contains(it)
            DefaultComponent(position = it, isMine = isMine)
        }.sortedWith(
            compareBy({ it.position.x }, { it.position.y })
        )
    }
}
