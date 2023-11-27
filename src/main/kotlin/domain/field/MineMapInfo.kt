package domain.field

data class MineMapInfo(
    val point: Point,
    val mineCount: Int
) {
    init {
        require(point.y > 0) { "높이는 0보다 커야 합니다." }
        require(point.x > 0) { "너비는 0보다 커야 합니다." }
        require(mineCount > 0) { "지뢰는 0보다 커야 합니다." }
        require(point.x * point.y >= mineCount) { "지뢰는 맵의 칸 수와 같거나 작아야 합니다." }
    }
}
