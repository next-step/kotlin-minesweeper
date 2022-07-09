package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo

object OutOfBoundaryValidator : MinePositionsValidator {
    private const val MESSAGE_OUT_OF_BOUNDARY = "범위를 넘어선 곳에 지뢰를 생성할 수 없습니다.\n유효하지 않은 좌표 목록: "

    override fun validate(minePositions: List<CellPosition>, minesweeperInfo: MinesweeperInfo) {
        val outOfBoundaryMines =
            minePositions.filter { minePosition -> !minePosition.isInBoundaryOf(minesweeperInfo) }
        require(outOfBoundaryMines.isEmpty()) {
            "$MESSAGE_OUT_OF_BOUNDARY $outOfBoundaryMines"
        }
    }
}
