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

    fun plantMine() {
        minePositionStrategy.getMinePositions().forEach {
            map[it] = Mine()
        }
        plantEmpty()
    }

    fun getCurrentMap(): Map<Position, MapItem> {
        return map.toMap()
    }

    private fun plantEmpty() {
        repeat(height) { y ->
            repeat(width) { x ->
                val mineCount = calculateSurroundingMineCount(x, y)
                map[Position(x, y)] = map[Position(x, y)] ?: Empty(surroundingMineCount = mineCount)
            }
        }
    }

    private val dxys = listOf(
        0 to -1, // 위
        1 to -1, // 오른쪽 위
        1 to 0, // 오른쪽
        1 to 1, // 오른쪽 아래
        0 to 1, // 아래
        -1 to 1, // 왼쪽 아래
        -1 to 0, // 왼쪽
        -1 to -1 // 왼쪽 위
    )

    private fun calculateSurroundingMineCount(x: Int, y: Int): Int {
        var mineCount = 0
        dxys.forEach { dxy ->
            val nx = x + dxy.first
            val ny = y + dxy.second
            if (nx in 0 until width && ny in 0 until height && map[Position(nx, ny)] is Mine) {
                mineCount++
            }
        }
        return mineCount
    }
}
