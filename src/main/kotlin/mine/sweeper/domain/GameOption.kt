package mine.sweeper.domain

import mine.sweeper.domain.value.Position

interface GameOption {
    val area: Int
    val widthVal: Int
    val minePositions: Set<Position>
    fun positionBy(index: Int): Position {
        val x = index / widthVal
        val y = index % widthVal
        return Position(x, y)
    }
}
