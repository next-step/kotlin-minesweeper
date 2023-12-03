package domain.strategy

import domain.BoardSettings
import domain.Point

interface CreatePointStrategy {
    fun createMinePoints(boardSettings: BoardSettings): List<Point>
}
