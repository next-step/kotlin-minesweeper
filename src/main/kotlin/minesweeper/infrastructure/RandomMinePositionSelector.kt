package minesweeper.infrastructure

import minesweeper.domain.FieldInfo
import minesweeper.domain.MineCount
import minesweeper.domain.MinePositionSelector
import minesweeper.domain.Position

class RandomMinePositionSelector : MinePositionSelector {
    override fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): Set<Position> {
        return (1..fieldInfo.getWidth()).flatMap { x ->
            (1..fieldInfo.getHeight()).map { y ->
                Position(x, y)
            }
        }.shuffled()
            .take(mineCount.count)
            .toSet()
    }
}
