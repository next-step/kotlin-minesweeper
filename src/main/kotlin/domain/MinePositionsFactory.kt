package domain

abstract class MinePositionsFactory {
    fun create(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
        return getMinePositionsFrom(minesweeperInfo).also { minePositions ->
            require(minePositions.distinct().size == minePositions.size) {
                MESSAGE_DUPLICATE_MINE_POSITIONS
            }
            require(minePositions.size == minesweeperInfo.mineCount) {
                "$MESSAGE_INVALID_MINE_COUNT ${minePositions.size}"
            }
            val outOfBoundaryMines =
                minePositions.filter { minePosition -> !minePosition.isInBoundaryOf(minesweeperInfo) }
            require(outOfBoundaryMines.isEmpty()) {
                "$MESSAGE_OUT_OF_BOUNDARY $outOfBoundaryMines"
            }
        }
    }

    protected abstract fun getMinePositionsFrom(minesweeperInfo: MinesweeperInfo): List<CellPosition>

    companion object {
        private const val MESSAGE_DUPLICATE_MINE_POSITIONS = "중복된 지뢰 좌표가 생성되었습니다."
        private const val MESSAGE_INVALID_MINE_COUNT = "사용자의 입력과 동일한 개수의 지뢰가 생성되어야 합니다.\n생성된 지뢰 개수: "
        private const val MESSAGE_OUT_OF_BOUNDARY = "범위를 넘어선 곳에 지뢰를 생성할 수 없습니다.\n유효하지 않은 좌표 목록: "
    }
}
