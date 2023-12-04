package mineswipper.domain.map

class Field(
    private val size: Size,
    positions: List<Position>
) {
    val field: Map<Int, Pedals>

    init {
        val initField: MutableMap<Int, Pedals> = mutableMapOf()
        repeat(size.height) { x ->
            initField[x] = pedalSetting(x, positions)
        }
        field = initField.toMap()
    }

    private fun pedalSetting(x: Int, positions: List<Position>): Pedals {
        val pedalList = (0 until size.width).map { y ->
            val position = Position(x, y)
            when (positions.contains(position)) {
                true -> Mine()
                false -> NormalPedal()
            }
        }
        return Pedals(pedalList)
    }
}
