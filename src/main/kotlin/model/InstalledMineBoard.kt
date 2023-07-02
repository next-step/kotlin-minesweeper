package model

import model.minemark.MineMark

data class InstalledMineBoard(
    val mineBoard: MineBoard,
) {
    val maxXPosition: Int by lazy { mineBoard.maxXPosition }
    val maxYPosition: Int by lazy { mineBoard.maxYPosition }

    fun replacedSafetyMark(replaceMarkMapper: (Position) -> (MineMark)): MineBoard {
        return mineBoard.replacedOnlySafetyMarks(replaceMarkMapper)
    }

    fun mineCounts(positions: Collection<Position>): Int {
        return mineBoard.mineCount(positions)
    }
}
