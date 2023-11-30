package domain

object RandomMineMap {

    fun newRandomMineMap(point: Point, mineCount: Int): ArrayMap =
        ArrayMap(randomFlattenMap(point, mineCount).chunked(point.x))

    private fun initialList(size: Int, isMine: Boolean): List<Spot> =
        List(size) { Spot(isMine) }

    private fun randomFlattenMap(point: Point, mineCount: Int): List<Spot> =
        (initialList(mineCount, true) + initialList(point.getArea() - mineCount, false))
            .shuffled()
}
