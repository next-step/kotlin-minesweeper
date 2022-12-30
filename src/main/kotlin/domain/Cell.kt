package domain

sealed class Cell {
    abstract val coordinate: Coordinate
}

data class Mine(override val coordinate: Coordinate) : Cell() {
    constructor(x: Int, y: Int) : this(Coordinate(Number(x), Number(y)))
}

data class Blank(
    override val coordinate: Coordinate,
    var minesAroundCount: Int = INITIAL_MINES_AROUND_COUNT
) : Cell() {
    constructor(x: Int, y: Int) : this(Coordinate(Number(x), Number(y)))
    fun changeMinesAroundCount(count: Int) {
        minesAroundCount = count
    }

    companion object {
        private const val INITIAL_MINES_AROUND_COUNT = 0
    }
}
