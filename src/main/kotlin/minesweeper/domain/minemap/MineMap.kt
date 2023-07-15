package minesweeper.domain.minemap

import minesweeper.domain.Position
import minesweeper.domain.strategy.MinePositioningStrategy
import minesweeper.domain.strategy.RandomMinePositionStrategy

class MineMap(
    private val mineMapConfig: MineMapConfig,
    private val minePositioningStrategy: MinePositioningStrategy = RandomMinePositionStrategy(mineMapConfig)
) {
    private val map: MutableMap<Position, MapItem> = mutableMapOf()

    init {
        plantMine()
        plantEmpty()
    }

    fun currentMap(): Map<Position, MapItem> {
        return map.toMap()
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
                map[position] = map[position] ?: Empty(surroundingMineCount = 0)
            }
    }
}
