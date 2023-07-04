package mine.sweeper.field.domain

import mine.sweeper.domain.Position

class Fields(
    val fields: List<Field>,
) {
    infix fun get(position: Position): Field {
        return fields.first { it.isSame(position) }
    }
}
