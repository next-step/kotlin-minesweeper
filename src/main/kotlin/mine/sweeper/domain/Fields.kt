package mine.sweeper.domain

import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class Fields(mapSize: MapSize) {
    private var fields: List<Field> = List(mapSize.area()) { index ->
        val x = index / mapSize.width.value
        val y = index % mapSize.width.value
        SafeField(Position(x, y))
    }

    infix fun get(position: Position): Field {
        return fields.first { it.isSame(position) }
    }

    fun findOrNull(position: Position): Field? {
        return fields.firstOrNull { it.isSame(position) }
    }

    fun remainingFieldCount(): Int {
        return fields.size - fields.count { it.checked }
    }

    fun size(): Int {
        return fields.size
    }

    fun setMine(position: Position) {
        fields = fields.map { if (it.isSame(position)) MineField(position) else it }
    }

    fun toSortedList(): List<Field> {
        return fields.toList().sortedWith(compareBy({ it.position.x }, { it.position.y }))
    }
}
