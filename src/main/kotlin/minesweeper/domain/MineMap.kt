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

    var mineOpened: Boolean = false
        private set

    init {
        plantMine()
        plantEmpty()
    }

    fun getCurrentMap(): Map<Position, MapItem> {
        return map.toMap()
    }

    fun open(position: Position) {
        when (map[position]) {
            is Empty -> openEmpty(position)
            is Mine -> mineOpened = true
            else -> throw IllegalArgumentException(
                "Invalid Position, x should be within $width and y should be within $height, actual : $position"
            )
        }
    }

    fun checkAllEmptyOpened(): Boolean {
        val emptyCount = height * width - mineMapConfig.mineCount
        val emptyOpenedCount = map.values.filterIsInstance<Empty>()
            .count { it.isOpened }
        return emptyCount - emptyOpenedCount == 0
    }

    /**
     * ### 선택한 위치가 빈칸일 경우 지뢰가 없는 인접한 칸을 모두 엽니다
     */
    private fun openEmpty(position: Position) {
        val mapItem = map[position]
        if (mapItem !is Empty || mapItem.isOpened) return
        mapItem.open()
        val mineCount = calculateSurroundingMineCount(position)
        if (mineCount > 0) return
        getAvailableAroundPositionBy(position)
            .map { Position(position.x + it.dx, position.y + it.dy) }
            .onEach { openEmpty(it) }
    }

    private fun plantMine() {
        minePositionStrategy.getMinePositions().forEach {
            map[it] = Mine()
        }
    }

    /**
     * ### 지도를 순회하며 지뢰가 아닌 장소에 빈칸을 채웁니다
     *
     * 빈칸을 채울때 주변 지뢰의 개수를 같이 기록합니다
     */
    private fun plantEmpty() {
        repeat(height) { y ->
            repeat(width) { x ->
                val position = Position(x, y)
                val mineCount = calculateSurroundingMineCount(position)
                map[position] = map[position] ?: Empty(surroundingMineCount = mineCount)
            }
        }
    }

    /**
     * ### 주어진 위치를 기준으로 8분면에 있는 지뢰의 합을 구합니다
     */
    private fun calculateSurroundingMineCount(position: Position): Int {
        return getAvailableAroundPositionBy(position)
            .filter { map[Position(position.x + it.dx, position.y + it.dy)] is Mine }
            .size
    }

    /**
     * ### 주어진 위치를 기준으로 탐색 가능한 8분면을 추출합니다
     */
    private fun getAvailableAroundPositionBy(position: Position): List<AroundPosition> {
        return AroundPosition.values()
            .filter { position.x + it.dx in 0 until width && position.y + it.dy in 0 until height }
    }
}
