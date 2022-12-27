package minesweeper.domain

class GameMap(
    val height: Int,
    val width: Int,
    val blocks: List<Block>
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): GameMap {
            require(height >= 3) { "세로 길이 3 이상이어야합니다" }
            require(width >= 3) { "가로 길이 3 이상이어야합니다" }
            require(height * width > mineCount) { "지뢰 개수가 전체 블록 개수보다 많을 수 없습니다." }
            return GameMap(
                height,
                width,
                List(height * width - mineCount) { CleanBlock() } +
                    List(mineCount) { MineBlock() }
            )
        }
    }
}
