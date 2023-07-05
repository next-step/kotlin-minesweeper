package mine.sweeper.application

import mine.sweeper.application.value.GameStatus
import mine.sweeper.domain.Field
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.view.dto.Fields
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class FieldsManager(
    mapSize: MapSize,
) {
    private var fields: List<Field> = List(mapSize.area()) { index ->
        val x = index / mapSize.width.value
        val y = index % mapSize.width.value
        SafeField(Position(x, y))
    }

    fun create(minePositions: Set<Position>): Fields {
        minePositions.forEach { position ->
            fields = fields.map { if (it.isSame(position)) MineField(position) else it }
            GridManager.doActionIfSafeField(position, fields) { it.increase() }
        }
        return toField()
    }

    fun findOrNull(position: Position): Field? {
        return fields.firstOrNull { it.isSame(position) }
    }

    fun open(field: Field): GameStatus {
        if (field.checked) return GameStatus.RE_TRY
        return field.open()
    }

    fun gridOpen(position: Position): GameStatus {
        GridManager.doActionIfSafeField(position, fields) { it.open() }
        return GameStatus.ON_PROGRESS
    }

    fun toField(): Fields {
        return Fields(fields)
    }
}
