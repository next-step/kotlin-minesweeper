package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Position

class SafeField(position: Position) : Field(position) {
    var value = 0
        private set

    val isEmpty: Boolean
        get() = value == 0

    fun increase() {
        value++
    }

    override fun open(): GameStatus {
        if (!checked) updateCheck()
        return GameStatus.ON_PROGRESS
    }
}
