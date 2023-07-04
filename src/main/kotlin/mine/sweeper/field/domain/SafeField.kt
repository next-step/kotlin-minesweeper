package mine.sweeper.field.domain

import mine.sweeper.domain.Position

class SafeField(position: Position) : Field(position) {
    override var value: String = "0"

    fun changeField() {
        value.toIntOrNull()?.let {
            value = (it + 1).toString()
        }
    }
}
