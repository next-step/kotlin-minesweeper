package mine.sweeper.domain

import mine.sweeper.application.value.GameStatus
import mine.sweeper.view.dto.Position

class SafeField(position: Position) : Field(position) {
    var value: Int = 0

    fun increase() {
        value++
    }

    override fun open(): GameStatus {
        if (!checked) {
            checked = true
        }
        return GameStatus.ON_PROGRESS
    }
}
