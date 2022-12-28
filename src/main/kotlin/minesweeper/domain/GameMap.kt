package minesweeper.domain

class GameMap(
    val height: Int,
    val width: Int,
    val blocks: Blocks
) {
    init {
        require(height >= 3) { "세로 길이 3 이상이어야합니다" }
        require(width >= 3) { "가로 길이 3 이상이어야합니다" }
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): GameMap {
            return GameMap(
                height,
                width,
                Blocks.of(height * width, mineCount)
            )
        }
    }
}
