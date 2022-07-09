package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo

object MineCountValidator : MinePositionsValidator {
    private const val MESSAGE_INVALID_MINE_COUNT = "사용자의 입력과 동일한 개수의 지뢰가 생성되어야 합니다.\n생성된 지뢰 개수: "

    override fun validate(minePositions: List<CellPosition>, minesweeperInfo: MinesweeperInfo) {
        require(minePositions.size == minesweeperInfo.mineCount) {
            "$MESSAGE_INVALID_MINE_COUNT ${minePositions.size}"
        }
    }
}
