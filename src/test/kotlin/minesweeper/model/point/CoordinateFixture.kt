package minesweeper.model.point

object CoordinateFixture {
    fun Pair<Int, Int>.toCoordinate(): Coordinate {
        return Coordinate(Vertical(this.first), Horizontal(this.second))
    }
}
