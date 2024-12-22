package minesweeper.infrastructrue

import minesweeper.domain.FieldInfo
import minesweeper.domain.MineCount
import minesweeper.domain.MinePositionSelector
import minesweeper.domain.Position

class CustomMinePositionSelector(private val minePositions: Set<Position>) : MinePositionSelector {
    override fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): Set<Position> {
        return minePositions
    }
}
