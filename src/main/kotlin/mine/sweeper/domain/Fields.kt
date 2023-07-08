package mine.sweeper.domain

import mine.sweeper.view.dto.Position

class Fields(private val fields: List<Field>) {
    val remainingFieldCount: Int
        get() = fields.size - fields.count { it.checked }

    val size: Int
        get() = fields.size

    val sortedList: List<Field>
        get() = fields.toList().sortedWith(compareBy({ it.position.x }, { it.position.y }))

    infix fun get(position: Position): Field? {
        return fields.find { it.isSame(position) }
    }
}
