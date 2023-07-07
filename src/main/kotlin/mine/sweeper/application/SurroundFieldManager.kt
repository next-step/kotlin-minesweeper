package mine.sweeper.application

import mine.sweeper.domain.value.SurroundPosition
import mine.sweeper.domain.Fields
import mine.sweeper.domain.SafeField
import mine.sweeper.view.dto.Position

object SurroundFieldManager {
    fun increase(position: Position, fields: Fields) {
        for (surroundPosition in SurroundPosition.values()) {
            val target = addPosition(position, surroundPosition)
            val safeField = safeFieldOrNull(fields, target) ?: continue
            safeField.increase()
        }
    }

    fun openEmptyFields(position: Position, fields: Fields, visited: HashSet<Position>) {
        for (surroundPosition in SurroundPosition.values()) {
            val target = addPosition(position, surroundPosition)
            if (visited.contains(target)) continue
            val safeField = openSafeField(target, fields, visited)
            safeField?.let {
                if (it.isEmpty()) openEmptyFields(target, fields, visited)
            }
        }
    }

    private fun addPosition(position: Position, surround: SurroundPosition) =
        Position(position.x + surround.x, position.y + surround.y)

    private fun safeFieldOrNull(fields: Fields, target: Position) = fields.findOrNull(target) as? SafeField

    private fun openSafeField(target: Position, fields: Fields, visited: HashSet<Position>): SafeField? {
        val safeField = safeFieldOrNull(fields, target) ?: return null
        visited.add(target)
        safeField.open()
        return safeField
    }
}
