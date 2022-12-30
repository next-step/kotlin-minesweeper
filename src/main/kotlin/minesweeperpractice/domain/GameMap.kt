package minesweeperpractice.domain

class GameMap(
    val height: Int,
    val width: Int,
    val blocks: Blocks
) {
    init {
        require(height >= MIN_HEIGHT) { "세로 길이 $MIN_HEIGHT 이상이어야합니다" }
        require(width >= MIN_WIDTH) { "가로 길이 $MIN_WIDTH 이상이어야합니다" }
    }

    companion object {
        private const val MIN_HEIGHT = 3
        private const val MIN_WIDTH = 3

        fun of(height: Int, width: Int, mineCount: Int): GameMap {
            return GameMap(
                height,
                width,
                Blocks.of(height * width, mineCount)
            )
        }
    }
}
