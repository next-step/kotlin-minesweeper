package model

data class InstalledMineBoard(
    val mineBoard: MineBoard,
) {
    val maxXPosition: Int by lazy { mineBoard.maxXPosition }
    val maxYPosition: Int by lazy { mineBoard.maxYPosition }

    fun replacedSafetyMark(countByPosition: (Position) -> (Int)): MineBoard {
        return mineBoard.replacedOnlySafetyMarks(countByPosition)
    }

    fun mineCounts(positions: Collection<Position>): Int {
        return mineBoard.mineCount(positions)
    }
}
