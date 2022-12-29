package domain

sealed class Cell {
    abstract val coordinate: Coordinate
}

data class Mine(override val coordinate: Coordinate) : Cell() {
    companion object {
        fun of(x: Int, y: Int) = Mine(Coordinate(Row(x), Column(y)))
    }
}

data class Blank(
    override val coordinate: Coordinate,
    val minesAroundCount: Int = 0
) : Cell() {
    companion object {
        fun of(x: Int, y: Int): Blank {
            return Blank(Coordinate(Row(x), Column(y)))
        }
    }
}
