package minesweeper.domain

import kotlin.random.Random

object RandomPositionSelector : PositionSelector {
    override fun select(
        mineMapMeta: MineMapMeta
    ): Position {
        val x = Random.nextInt(1, mineMapMeta.width + 1)
        val y = Random.nextInt(1, mineMapMeta.height + 1)
        return Position(y, x)
    }
}
