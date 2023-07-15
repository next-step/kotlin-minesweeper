package minesweeper_tdd.domain

import minesweeper_tdd.domain.minemap.MineMap
import minesweeper_tdd.domain.minemap.MineMapConfig
import minesweeper_tdd.domain.strategy.MinePositioningStrategy
import minesweeper_tdd.domain.strategy.RandomMinePositionStrategy

/**
 * ### 지뢰 찾기 게임을 제어하는 주체입니다
 */
class MineSweeperGame(
    private val mineMapConfig: MineMapConfig,
    private val minePositioningStrategy: MinePositioningStrategy = RandomMinePositionStrategy(mineMapConfig),
    private val openPosition: () -> Position,
    private val gameProgress: (MineMap) -> Unit = {}
) {
    fun run(): GameResult {
        val mineMap = MineMap(mineMapConfig, minePositioningStrategy)
        while (mineMap.mineOpened.not() && mineMap.checkAllEmptyOpened().not()) {
            mineMap.open(openPosition())
            gameProgress(mineMap)
        }
        return GameResult(mineMap.mineOpened.not())
    }
}

data class GameResult(val win: Boolean)
