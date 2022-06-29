package minesweeper.dto

class MineBoardCreateDto(
    val width: Int,
    val height: Int,
    val mineCount: Int,
) {

    init {
        validateMinWidth(width)
        validateMinHeight(height)
        validateMineCount(width, height, mineCount)
    }

    private fun validateMinWidth(width: Int) =
        require(width >= MIN_WIDTH) { "너비는 최소 $MIN_WIDTH 이상이어야 합니다." }

    private fun validateMinHeight(height: Int) =
        require(height >= MIN_HEIGHT) { "높이는 최소 $MIN_HEIGHT 이상이어야 합니다." }

    private fun validateMineCount(width: Int, height: Int, mineCount: Int) {
        require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 최소 ${MIN_MINE_COUNT}개 이상이어야 합니다." }

        val maxMineCount = width * height
        require(mineCount <= maxMineCount) { "지뢰 개수는 ${maxMineCount}개를 초과할 수 없습니다." }
    }

    companion object {
        private const val MIN_WIDTH = 1
        private const val MIN_HEIGHT = 1
        private const val MIN_MINE_COUNT = 1
    }
}
