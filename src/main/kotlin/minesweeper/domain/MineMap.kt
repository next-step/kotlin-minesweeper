package minesweeper.domain

/**
 * ### 지뢰를 매설하는 지도를 표현하는 객체입니다.
 */
data class MineMap(
    private val mineMapConfig: MineMapConfig,
    private val minePositionStrategy: MinePositionStrategy = RandomMinePositionStrategy(mineMapConfig)
) {

    private val map: MutableList<MutableList<String>> = MutableList(mineMapConfig.height) {
        MutableList(mineMapConfig.width) { EMPTY_SYMBOL }
    }

    fun plantMine() {
        minePositionStrategy.getMinePositions().forEach {
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
