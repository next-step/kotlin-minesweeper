package mine.sweeper.application

import mine.sweeper.application.value.SurroundPosition
import mine.sweeper.domain.Field
import mine.sweeper.domain.SafeField
import mine.sweeper.view.dto.Position

object GridManager {
    fun doActionIfSafeField(position: Position, fields: List<Field>, action: (SafeField) -> Unit) {
        for (surroundPosition in SurroundPosition.values()) {
            val targetPosition = Position(position.x + surroundPosition.x, position.y + surroundPosition.y)
            val safeField = fields.firstOrNull { it.isSame(targetPosition) } as? SafeField ?: continue
            action(safeField)
        }
    }
}
