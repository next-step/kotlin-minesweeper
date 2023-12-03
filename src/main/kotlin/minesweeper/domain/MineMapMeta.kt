package minesweeper.domain

data class MineMapMeta(
    val height: Int,
    val width: Int,
    val mineCount: Int
) {
    init {
        require(height > 0) { "입력값: $height, 높이는 0 이거나 음수일 수 없습니다" }
        require(width > 0) { "입력값: $width, 너비는 0 이거나 음수일 수 없습니다" }
        require(mineCount > 0) { "입력값: $mineCount, 지뢰 개수는 0 이거나 음수일 수 없습니다" }
        require(height * width >= mineCount) { "입력값: ${height * width}, 지뢰 개수는 (높이 x 너비) 개수를 초과할 수 없습니다" }
    }

    fun getCellCount(): Int {
        return height * width
    }
}
