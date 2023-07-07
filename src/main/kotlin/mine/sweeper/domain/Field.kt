package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.view.dto.Position

sealed class Field(val position: Position) {
    var checked = false
        private set

    abstract fun open(): GameStatus

    fun isSame(paramPosition: Position): Boolean {
        return this.position == paramPosition
    }

    fun updateCheck() {
        checked = true
    }
}
