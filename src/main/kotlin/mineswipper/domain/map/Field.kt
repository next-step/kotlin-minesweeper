package mineswipper.domain.map

import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Row
import mineswipper.domain.map.position.Size

class Field(
    private val size: Size,
    minePositions: Positions
) {
    val field: Map<Row, Pedals>

    init {
        val initField: MutableMap<Row, Pedals> = mutableMapOf()
        repeat(size.height) { x ->
            initField[Row(x)] = pedalSetting(x, minePositions)
        }
        field = initField.toMap()
    }

    private fun pedalSetting(x: Int, minePositions: Positions): Pedals {
        val pedalList = (0 until size.width).map { y ->
            val position = Position(x, y)
            createPedal(minePositions, position)
        }
        return Pedals(pedalList)
    }

    private fun createPedal(
        minePositions: Positions, position: Position
    ): Pedal {
        if (minePositions.contains(position)) return Mine()
        return NormalPedal()
    }
}
