package mine.sweeper.field.domain

import mine.sweeper.domain.MapSize
import mine.sweeper.domain.Position

class FieldsManager(
    mapSize: MapSize,
) {
    private var fields: List<Field> = List(mapSize.height.value * mapSize.width.value) { index ->
        val x = index / mapSize.width.value
        val y = index % mapSize.width.value
        SafeField(Position(x, y))
    }

    fun toFields(): Fields {
        return Fields(fields)
    }

    fun changeMineField(position: Position) {
        if (fields.none { it.isSame(position) }) return

        val mineField = MineField(position)
        fields = fields.map { if (it.isSame(position)) mineField else it }

        for ((plusX, plusY) in SURROUND_POSITION) {
            val newPosition = Position(position.x + plusX, position.y + plusY)
            val safeField = fields.firstOrNull { it.isSame(newPosition) } as? SafeField ?: continue
            safeField.changeField()
        }
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
            1 to 1,
        )
    }
}
