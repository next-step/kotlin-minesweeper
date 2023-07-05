package mine.sweeper.domain

import mine.sweeper.application.value.GameStatus
import mine.sweeper.view.dto.Position

sealed class Field(val position: Position) {
    var checked = false

    fun isSame(paramPosition: Position): Boolean {
        return this.position == paramPosition
    }

    abstract fun open(): GameStatus
}
