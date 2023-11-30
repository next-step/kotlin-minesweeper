package domain.strategy

import domain.BoardSettings

interface CreatePointStrategy {
    fun createMinePoints(boardSettings: BoardSettings): List<Int>
}
