package mine.sweeper.application

import mine.sweeper.domain.Fields
import mine.sweeper.domain.value.GameStatus.ON_PROGRESS
import mine.sweeper.view.dto.Position

class MineSweeperGame(private val map: MineSweeperMap) {
    var status = ON_PROGRESS

    val isProgress: Boolean
        get() = status === ON_PROGRESS

    val fields: Fields
        get() = map.fields

    val gameResult: GameStatus
        get() = status

    fun select(position: Position) {
        val field = map.findUncheckOrNull(position) ?: return
        status = map.open(position, field)
    }
}
