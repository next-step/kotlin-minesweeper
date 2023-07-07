package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.view.dto.Position

class SafeField(position: Position) : Field(position) {
    var value: Int = 0

    override fun open(): GameStatus {
        if (!checked) updateCheck()
        return GameStatus.ON_PROGRESS
    }

    fun increase() = value++

    fun isEmpty(): Boolean {
        return value == 0
    }
}
