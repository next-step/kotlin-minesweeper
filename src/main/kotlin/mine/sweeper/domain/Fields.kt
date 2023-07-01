package mine.sweeper.domain

import mine.sweeper.domain.value.Field
import mine.sweeper.domain.value.Position

class Fields(mapSize: MapSize) {

    private val fields: MutableMap<Position, Field> = mutableMapOf()

    init {
        repeat(mapSize.height()) { x ->
            repeat(mapSize.width()) { y ->
                fields[Position(x, y)] = Field()
            }
        }
    }

    fun entire(): List<List<Field>> {
        return fields.keys.groupBy { it.x }.values.map { positions ->
            positions.sortedBy { it.y }.map { fields.getValue(it) }
        }
    }

    fun changeMineField(position: Position) {
        require(fields.containsKey(position))

        fields[position]?.mineField()

        for ((plusX, plusY) in SURROUND_POSITION) {
            val newPosition = Position(position.x + plusX, position.y + plusY)
            changeIfDigit(newPosition)
        }
    }

    private fun changeIfDigit(newPosition: Position) {
        fields[newPosition]?.let { fieldValue ->
            fieldValue.value.toIntOrNull()?.let {
                fieldValue.value = (it + 1).toString()
            }
        }
    }

    fun from(position: Position): Field? {
        require(fields.containsKey(position))
        return fields[position]
    }

    companion object {
        private val SURROUND_POSITION = listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1
        )
    }
}
