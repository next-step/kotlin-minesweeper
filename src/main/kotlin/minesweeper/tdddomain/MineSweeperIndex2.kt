package minesweeper.tdddomain

import minesweeper.domain.IndexSquare
import minesweeper.domain.MineStatus
import minesweeper.domain.Position
import minesweeper.domain.PositionStatus

class MineSweeperIndex2(val position: Position, val mineStatus: MineStatus) {
    var openStatus: PositionStatus
        private set

    init {
        openStatus = PositionStatus.CLOSED
    }

    constructor(x: Int, y: Int, mineStatus: MineStatus) : this(Position(x, y), mineStatus)

    fun open() {
        openStatus = PositionStatus.OPENED
    }

    fun mineCount(mineSweeperIndexes: List<MineSweeperIndex2>): Int {
        return IndexSquare.squareIndex(this, mineSweeperIndexes)
            .count { it.mineStatus == MineStatus.MINE }
    }
}
