package minesweeper.domain.vo

class Position private constructor(val x: PositionX, val y: PositionY) {
    fun getNeighbors(xLimit: Int, yLimit: Int): List<Position> {
        val neighborsX = listOfNotNull(x.left(), x.right(), x).filter { it.value < xLimit }
        val neighborsY = listOfNotNull(y.top(), y.bottom(), y).filter { it.value < yLimit }
        val getPositionsOf: (PositionX) -> List<Position> = { xPos ->
            neighborsY.map { yPos -> Position(xPos, yPos) }
        }
        return neighborsX
            .flatMap { xPos -> getPositionsOf(xPos) }
            .filterNot { it == this }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        return y == other.y
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    companion object {
        private val POSITION_CACHE = mutableMapOf<String, Position>()

        fun of(x: Int, y: Int): Position {
            return cache(x, y) ?: Position(PositionX(x), PositionY(y))
        }

        fun of(x: PositionX, y: PositionY): Position {
            return of(x.value, y.value)
        }

        private fun cache(x: Int, y: Int): Position? {
            if (canCache(x) && canCache(y)) {
                val key = key(x, y)
                return POSITION_CACHE.getOrPut(key) { Position(PositionX(x), PositionY(y)) }
            }
            return null
        }

        private fun canCache(number: Int) = number in 0..10

        private fun key(x: Int, y: Int) = "$x:$y"
    }
}
