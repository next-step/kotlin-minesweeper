package mine.sweeper.domain

import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class Fields(mapSize: MapSize) {
    private var fields: List<Field> = List(mapSize.area) { index ->
        val x = index / mapSize.width.value
        val y = index % mapSize.width.value
        SafeField(Position(x, y))
    }

    val remainingFieldCount: Int
        get() = fields.size - fields.count { it.checked }

    val size: Int
        get() = fields.size

    val sortedList: List<Field>
        get() = fields.toList().sortedWith(compareBy({ it.position.x }, { it.position.y }))

    infix fun get(position: Position): Field {
        return fields.first { it.isSame(position) }
    }

    fun findOrNull(position: Position): Field? {
        return fields.firstOrNull { it.isSame(position) }
    }

    fun setMine(position: Position) {
        fields = fields.map { if (it.isSame(position)) MineField(position) else it }
    }
}
