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

        markGeneration()
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

    private fun markGeneration(position: Position = Position(0, 0)) {
        val pedal = findPedal(position)
        if (pedal.mark != null) return

        val aroundPositions = position.getAroundPositions(size)
        val count = aroundPositions.positions.count {
            val findPedal = findPedal(it)
            findPedal is Mine
        }

        pedal.mark = Mark(count.toString())

        aroundPositions.positions.forEach {
            markGeneration(it)
        }
    }

    fun findPedal(position: Position): Pedal {
        val pedals = field[position.toRow()]
        require(pedals != null) { }

        val pedal = pedals.get(position.x)
        return pedal
    }
}
