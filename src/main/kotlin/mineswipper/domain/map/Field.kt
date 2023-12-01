package mineswipper.domain.map

class Field(
    width: Int,
    height: Int,
    positions: List<Position>
) {
    val field: Map<Int, List<Pedal>>

    init {
        val initField: MutableMap<Int, List<Pedal>> = mutableMapOf()
        repeat(height) {x ->
            initField[x] = (0..width).map {y ->
                val position = Position(x, y)
                when (positions.contains(position)) {
                    true -> Mine()
                    false -> NormalPedal()
                }
            }
        }

        field = initField.toMap()
    }
}
