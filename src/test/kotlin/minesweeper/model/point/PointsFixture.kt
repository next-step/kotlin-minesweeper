package minesweeper.model.point

object PointsFixture {
    fun make(vararg pairs: Pair<Int, Int>): Points {
        return Points(
            pairs.asSequence()
                .map { Coordinate(Vertical(it.first), Horizontal(it.second)) }
                .map { it to TileType.MINE }
                .toMap()
        )
    }
}
