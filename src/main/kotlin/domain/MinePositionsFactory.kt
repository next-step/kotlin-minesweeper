package domain

import domain.validator.DuplicatePositionValidator
import domain.validator.MineCountValidator
import domain.validator.OutOfBoundaryValidator

abstract class MinePositionsFactory {
    fun create(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
        return getMinePositionsFrom(minesweeperInfo).also { minePositions ->
            DuplicatePositionValidator.validate(minePositions, minesweeperInfo)
            MineCountValidator.validate(minePositions, minesweeperInfo)
            OutOfBoundaryValidator.validate(minePositions, minesweeperInfo)
        }
    }

    protected abstract fun getMinePositionsFrom(minesweeperInfo: MinesweeperInfo): List<CellPosition>
}
