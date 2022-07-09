package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo

interface MinePositionsValidator {
    @Throws(IllegalArgumentException::class)
    fun validate(minePositions: List<CellPosition>, minesweeperInfo: MinesweeperInfo)
}
