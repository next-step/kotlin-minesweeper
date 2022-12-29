package domain

sealed class Cell {
    abstract val coordinate: Coordinate
}

data class Mine(override val coordinate: Coordinate) : Cell() {
    companion object {
        fun of(x: Int, y: Int) = Mine(Coordinate(Number(x), Number(y)))
    }
}

data class Blank(
    override val coordinate: Coordinate,
    var minesAroundCount: Int = INITIAL_MINES_AROUND_COUNT
) : Cell() {
    fun changeMinesAroundCount(count: Int) {
        minesAroundCount = count
    }

    companion object {
        private const val INITIAL_MINES_AROUND_COUNT = 0
        fun of(x: Int, y: Int): Blank {
            return Blank(Coordinate(Number(x), Number(y)))
        }
    }
}
