package mineswipper.domain.map

class Field(
    private val size: Size,
    minePositions: List<Position>
) {
    val field: Map<Row, Pedals>

    init {
        val initField: MutableMap<Row, Pedals> = mutableMapOf()
        repeat(size.height) { x ->
            initField[Row(x)] = pedalSetting(x, minePositions)
        }
        field = initField.toMap()
    }

    private fun pedalSetting(x: Int, minePositions: List<Position>): Pedals {
        val pedalList = (0 until size.width).map { y ->
            val position = Position(x, y)
            createPedal(minePositions, position)
        }
        return Pedals(pedalList)
    }

    private fun createPedal(
        minePositions: List<Position>, position: Position
    ): Pedal {
        if (minePositions.contains(position)) return Mine()
        return NormalPedal()
    }
}
