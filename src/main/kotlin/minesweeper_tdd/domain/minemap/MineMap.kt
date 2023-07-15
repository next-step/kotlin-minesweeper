package minesweeper_tdd.domain.minemap

import minesweeper_tdd.domain.Position
import minesweeper_tdd.domain.strategy.MinePositioningStrategy
import minesweeper_tdd.domain.strategy.RandomMinePositionStrategy

class MineMap(
    val mineMapConfig: MineMapConfig,
    private val minePositioningStrategy: MinePositioningStrategy = RandomMinePositionStrategy(mineMapConfig)
) {
    private val map: MutableMap<Position, MapItem> = mutableMapOf()

    var mineOpened: Boolean = false
        private set

    init {
        plantMine()
        plantEmpty()
    }

    fun currentMap(): Map<Position, MapItem> {
        return map.toMap()
    }

    fun open(position: Position) {
        when (map[position]) {
            is Empty -> openEmpty(position)
            is Mine -> mineOpened = true
            else -> throw IllegalArgumentException(
                "Invalid Position, x should be within ${mineMapConfig.width} and y should be within ${mineMapConfig.height}, actual : $position"
            )
        }
    }

    fun checkAllEmptyOpened(): Boolean {
        return map.values.filterIsInstance<Empty>().all { it.isOpened }
    }

    /**
     * ### 지뢰 매설 위치 결정 전략에서 추출한 지뢰 위치를 통해 지뢰를 매설합니다.
     */
    private fun plantMine() {
        minePositioningStrategy.getMinePositions().forEach {
            map[it] = Mine()
        }
    }

    /**
     * ### 지도를 순회하며 지뢰가 아닌 장소에 빈칸을 채웁니다
     */
    private fun plantEmpty() {
        Position.all(mineMapConfig.width, mineMapConfig.height)
            .forEach { position ->
                val surroundingMineCount = calculateSurroundingMineCount(position)
                map[position] = map[position] ?: Empty(surroundingMineCount = surroundingMineCount)
            }
    }

    /**
     * ### 주어진 위치를 기준으로 8분면에 있는 지뢰의 합을 구합니다
     */
    private fun calculateSurroundingMineCount(position: Position): Int {
        return position.nearby(maxX = mineMapConfig.width, maxY = mineMapConfig.height)
            .count { map[it] is Mine }
    }

    /**
     * ### 선택한 위치가 빈칸일 경우 지뢰가 없는 인접한 칸을 모두 엽니다
     */
    private fun openEmpty(position: Position) {
        val mapItem = map[position]
        if (mapItem !is Empty || mapItem.isOpened) return
        mapItem.open()
        if (calculateSurroundingMineCount(position) > 0) return
        position.nearby(maxX = mineMapConfig.width, maxY = mineMapConfig.height)
            .onEach { openEmpty(it) }
    }
}
