package mine.sweeper.application

import mine.sweeper.domain.Fields
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.GameStatus.GAME_OVER
import mine.sweeper.domain.value.GameStatus.ON_PROGRESS
import mine.sweeper.view.dto.Position

class MineSweeperGame(private val map: MineSweeperMap) {
    private var status = ON_PROGRESS

    val isProgress: Boolean
        get() = status === ON_PROGRESS

    val fields: Fields
        get() = map.fields

    val gameResult: GameStatus
        get() = status

    fun select(position: Position) {
        status = map.open(position)
        if (isGameComplete()) status = GameStatus.COMPLETE
    }

    private fun isGameComplete(): Boolean {
        return status != GAME_OVER && map.mineCount.value == fields.remainingFieldCount
    }
}
