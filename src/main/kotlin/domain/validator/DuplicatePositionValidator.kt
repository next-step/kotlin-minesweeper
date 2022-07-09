package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo

object DuplicatePositionValidator : MinePositionsValidator {
    private const val MESSAGE_DUPLICATE_MINE_POSITIONS = "중복된 지뢰 좌표가 생성되었습니다."

    override fun validate(minePositions: List<CellPosition>, minesweeperInfo: MinesweeperInfo) {
        require(minePositions.distinct().size == minePositions.size) {
            MESSAGE_DUPLICATE_MINE_POSITIONS
        }
    }
}
