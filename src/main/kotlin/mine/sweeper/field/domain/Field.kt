package mine.sweeper.field.domain

import mine.sweeper.domain.Position

abstract class Field(val position: Position) {
    abstract val value: String
    fun isSame(paramPosition: Position): Boolean {
        return this.position == paramPosition
    }
}
