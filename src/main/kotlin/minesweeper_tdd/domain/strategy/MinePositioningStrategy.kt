package minesweeper_tdd.domain.strategy

import minesweeper_tdd.domain.Positions

/**
 * ### 지뢰를 어떤 위치에 매설할지 결정하는 전략입니다
 */
interface MinePositioningStrategy {
    fun getMinePositions(): Positions
}
