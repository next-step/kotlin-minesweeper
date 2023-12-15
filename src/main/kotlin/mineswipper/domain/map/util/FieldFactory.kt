package mineswipper.domain.map.util

import mineswipper.domain.map.Field
import mineswipper.domain.map.Mine
import mineswipper.domain.map.NormalPedal
import mineswipper.domain.map.Pedal
import mineswipper.domain.map.Pedals
import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Row
import mineswipper.domain.map.position.Size

class FieldFactory(
    private val size: Size
) {
    fun createField(
//        size: Size,
        minePositions: Positions
    ): Field {
        val initField: MutableMap<Row, Pedals> = mutableMapOf()
        repeat(size.height) { x ->
            initField[Row(x)] = pedalSetting(x, minePositions)
        }

        val fakeField = Field(initField.toMap())

        MarkGenerator.markGeneration(fakeField)

        return fakeField
    }
    private fun createPedal(
        minePositions: Positions,
        position: Position
    ): Pedal {
        return if (minePositions.contains(position)) Mine() else NormalPedal()
    }

    private fun pedalSetting(x: Int, minePositions: Positions): Pedals {
        val pedalList = (0 until size.width).map { y ->
            val position = Position(x, y)
            createPedal(minePositions, position)
        }
        return Pedals(pedalList)
    }
}
