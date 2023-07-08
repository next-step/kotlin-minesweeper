package minesweeper.domain

/**
 * ### 지뢰를 매설하는 지도를 표현하는 객체입니다.
 */
data class MineMap(
    private val mineMapConfig: MineMapConfig,
    private val minePositionStrategy: MinePositionStrategy = RandomMinePositionStrategy(mineMapConfig)
) {

    private val map: MutableMap<Position, MapItem> = mutableMapOf()

    init {
        repeat(mineMapConfig.height) { y ->
            repeat(mineMapConfig.width) { x ->
                map[Position(x, y)] = Empty()
            }
        }
    }

    fun plantMine() {
        minePositionStrategy.getMinePositions().forEach {
            map[it] = Mine()
        }
    }

    override fun toString(): String {
        return buildString {
            repeat(mineMapConfig.height) { y ->
                repeat(mineMapConfig.width) { x ->
                    append("${map[Position(x, y)]?.item} ")
                }
                appendLine()
            }
        }
    }

    companion object {
        const val MINE_SYMBOL = "*"
        const val EMPTY_SYMBOL = "C"
    }
}
