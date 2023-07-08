package minesweeper.domain

/**
 * ### 지뢰를 매설하는 지도를 표현하는 객체입니다.
 */
data class MineMap(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
    private val minePositionStrategy: MinePositionStrategy = RandomMinePositionStrategy(height, width)
) {
    init {
        require(height > 0) { "height must be greater than zero, actual : $height" }
        require(width > 0) { "width must be greater than zero, actual : $width" }
        require(mineCount > 0) { "mineCount must be greater than zero, actual : $mineCount" }
    }

    private val map: MutableList<MutableList<String>> = MutableList(height) { MutableList(width) { EMPTY_SYMBOL } }

    fun plantMine() {
        minePositionStrategy.getMinePositions(mineCount)
            .forEach {
                map[it.y][it.x] = MINE_SYMBOL
            }
    }

    override fun toString(): String {
        return map.joinToString("\n") { it.joinToString(" ") }
    }

    companion object {
        const val MINE_SYMBOL = "*"
        const val EMPTY_SYMBOL = "C"
    }
}
