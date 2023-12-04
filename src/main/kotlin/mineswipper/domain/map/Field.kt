package mineswipper.domain.map

class Field(
    private val size: Size,
    positions: List<Position>
) {
    constructor(width: Int, height: Int, positions: List<Position>) : this(Size(width, height), positions)

    val field: Map<Int, List<Pedal>>

    init {
        val initField: MutableMap<Int, List<Pedal>> = mutableMapOf()
        repeat(size.height) { x ->
            initField[x] = pedalSetting(x, positions)
        }
        field = initField.toMap()
    }

    private fun pedalSetting(
        x: Int,
        positions: List<Position>
    ) = (0..size.width).map { y ->
        val position = Position(x, y)
        when (positions.contains(position)) {
            true -> Mine()
            false -> NormalPedal()
        }
    }
}
