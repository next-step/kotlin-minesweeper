package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.view.dto.Position

sealed class Field(val position: Position) {
    var checked = false
        private set

    fun isSame(paramPosition: Position): Boolean {
        return this.position == paramPosition
    }

    fun updateCheck() {
        checked = true
    }

    abstract fun open(): GameStatus
}
