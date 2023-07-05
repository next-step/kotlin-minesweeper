package mine.sweeper.view.dto

import mine.sweeper.domain.Field

data class Fields(
    val fields: List<Field>,
) {
    infix fun get(position: Position): Field {
        return fields.first { it.isSame(position) }
    }

    fun remainingFieldCount(): Int {
        return fields.size - fields.count { it.checked }
    }
}
