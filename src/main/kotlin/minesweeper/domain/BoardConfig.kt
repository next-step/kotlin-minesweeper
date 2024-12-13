package minesweeper.domain

data class BoardConfig(
    val height: Int,
    val width: Int,
    val mineCount: Int,
    val minePlacementStrategy: MinePlacementStrategy = RandomMinePlacementStrategy(),
) {
    init {
        require(height > 0) { "행은 1 이상이어야 합니다." }
        require(width > 0) { "열은 1 이상이어야 합니다." }
        require(mineCount > 0) { "지뢰 개수는 1 이상이어야 합니다." }
        require(height * width > mineCount) { "지뢰 개수는 전체 칸의 개수보다 작아야 합니다." }
    }

    fun getLandCount() = height * width - mineCount

    fun placeMines(): List<Int> {
        return minePlacementStrategy.placeMines(height, width, mineCount)
    }
}
