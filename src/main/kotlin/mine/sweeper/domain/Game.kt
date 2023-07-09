package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus

class Game(val fields: Fields) {
    private var status = GameStatus.ON_PROGRESS

    val isProgress: Boolean
        get() = status === GameStatus.ON_PROGRESS

    val gameResult: GameStatus
        get() = status

    fun select(position: Position) {
        status = fields.open(position)
        if (isGameComplete()) status = GameStatus.COMPLETE
    }

    private fun isGameComplete(): Boolean {
        val mineCount = fields.mineCount
        val remainingFieldCount = fields.remainingFieldCount
        return status != GameStatus.GAME_OVER && mineCount == remainingFieldCount
    }
}
