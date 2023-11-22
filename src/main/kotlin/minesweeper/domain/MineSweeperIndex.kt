package minesweeper.domain

class MineSweeperIndex(val position: Position, val mineStatus: MineStatus) {
    var openStatus: PositionStatus
        private set

    init {
        openStatus = PositionStatus.CLOSED
    }

    constructor(x: Int, y: Int, mineStatus: MineStatus) : this(Position(x, y), mineStatus)

    fun open() {
        openStatus = PositionStatus.OPENED
    }

    fun mineCount(mineSweeperIndexes: MineSweeperMap): Int {
        return IndexSquare.squareIndex(this, mineSweeperIndexes)
            .count { it.mineStatus == MineStatus.MINE }
    }

    fun isMine(): Boolean {
        return mineStatus == MineStatus.MINE
    }
}
