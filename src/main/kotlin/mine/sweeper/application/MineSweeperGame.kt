package mine.sweeper.application

import mine.sweeper.domain.value.GameStatus.ON_PROGRESS
import mine.sweeper.domain.Fields
import mine.sweeper.view.dto.Position

class MineSweeperGame(private val map: MineSweeperMap) {
    var status = ON_PROGRESS

    fun open(position: Position) {
        val field = map.findUncheckOrNull(position) ?: return
        status = map.open(position, field)
    }

    fun onProgress(): Boolean {
        return status === ON_PROGRESS
    }

    fun getResult(): Fields {
        return map.fields
    }
}
