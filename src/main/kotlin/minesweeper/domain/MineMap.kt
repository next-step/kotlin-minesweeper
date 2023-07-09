package minesweeper.domain

/**
 * ### 지뢰를 매설하는 지도를 표현하는 객체입니다.
 */
data class MineMap(
    private val mineMapConfig: MineMapConfig,
    private val minePositionStrategy: MinePositionStrategy = RandomMinePositionStrategy(mineMapConfig)
) {

    private val map: MutableMap<Position, MapItem> = mutableMapOf()

    val height: Int = mineMapConfig.height

    val width: Int = mineMapConfig.width

    init {
        plantMine()
        plantEmpty()
    }

    fun getCurrentMap(): Map<Position, MapItem> {
        return map.toMap()
    }

    private fun plantMine() {
        minePositionStrategy.getMinePositions().forEach {
            map[it] = Mine()
        }
    }

    private fun plantEmpty() {
        repeat(height) { y ->
            repeat(width) { x ->
                val mineCount = calculateSurroundingMineCount(x, y)
                map[Position(x, y)] = map[Position(x, y)] ?: Empty(surroundingMineCount = mineCount)
            }
        }
    }

    private fun calculateSurroundingMineCount(x: Int, y: Int): Int {
        return AroundPosition.values()
            .filter { x + it.dx in 0 until width && y + it.dy in 0 until height }
            .filter { map[Position(x + it.dx, y + it.dy)] is Mine }
            .size
    }
}
