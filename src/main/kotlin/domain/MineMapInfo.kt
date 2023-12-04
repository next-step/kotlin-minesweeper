package domain

data class MineMapInfo(
    val point: Point,
    val mineCount: Int
) {

    init {
        require(point.y > 0)
        require(point.x > 0)
        require(mineCount in 0 until point.getArea())
    }
}
