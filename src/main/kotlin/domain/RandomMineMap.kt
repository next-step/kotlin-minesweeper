package domain

object RandomMineMap {

    fun newMineMap(point: Point, mineCount: Int): List<List<Spot>> =
        (initialList(mineCount, true) + initialList(point.getArea() - mineCount, false))
            .shuffled()
            .chunked(point.x)


    private fun initialList(size: Int, isMine: Boolean): List<Spot> =
        List(size) { Spot(isMine) }
}
