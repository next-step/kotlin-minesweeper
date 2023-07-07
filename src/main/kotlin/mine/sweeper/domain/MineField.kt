package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.view.dto.Position

class MineField(position: Position) : Field(position) {
    override fun open(): GameStatus {
        return GameStatus.GAME_OVER
    }
}
