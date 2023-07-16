package minesweeper_tdd.domain

import minesweeper_tdd.domain.minemap.MineMap
import minesweeper_tdd.domain.minemap.MineMapConfig
import minesweeper_tdd.domain.strategy.MinePositioningStrategy
import minesweeper_tdd.domain.strategy.RandomMinePositionStrategy

internal typealias OnPositionProduced = () -> Position
internal typealias OnGameProgressed = (MineMap) -> Unit

/**
 * ### 지뢰 찾기 게임을 제어하는 주체입니다
 */
class MineSweeperGame(
    private val mineMapConfig: MineMapConfig,
    private val minePositioningStrategy: MinePositioningStrategy = RandomMinePositionStrategy(mineMapConfig),
    private val onPositionProduced: OnPositionProduced,
    private val onGameProgressed: OnGameProgressed = {}
) {
    fun run(): GameResult {
        val mineMap = MineMap(mineMapConfig, minePositioningStrategy)
        while (mineMap.open(onPositionProduced()) && mineMap.checkAllEmptyOpened().not()) {
            onGameProgressed(mineMap)
        }
        return GameResult(mineMap.checkAllEmptyOpened())
    }
}

data class GameResult(val win: Boolean)
