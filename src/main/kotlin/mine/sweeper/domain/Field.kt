package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Position

sealed class Field(val position: Position) {
    var checked = false
        private set

    fun isSame(target: Position): Boolean {
        return position == target
    }

    fun updateCheck() {
        checked = true
    }

    abstract fun open(): GameStatus
}
