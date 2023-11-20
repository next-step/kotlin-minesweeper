package vo

data class MineMapInfo(
    val height: Int,
    val width: Int,
    val mineCount: Int
) {
    init {
        require(height > 0) { "높이는 0보다 커야 합니다." }
        require(width > 0) { "너비는 0보다 커야 합니다." }
        require(mineCount > 0) { "지뢰는 0보다 커야 합니다." }
        require(height * width > mineCount) { "지뢰는 맵의 칸 수보다 작아야 합니다." }
    }
}
