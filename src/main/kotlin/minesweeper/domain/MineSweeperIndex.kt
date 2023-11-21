package minesweeper.domain

class MineSweeperIndex(val position: Position, status: PositionStatus = PositionStatus.CLOSED) {

    var status: PositionStatus = status
        private set

    fun mineCount(mines: Mines, mineSweeperIndexes: MineSweeperIndexes): Int {
        if (mines.isMine(this)) return MINE
        return IndexSquare.squareIndex(position, mineSweeperIndexes).mineSweeperIndexes
            .count { mines.isMine(it) }
    }

    fun open() {
        status = PositionStatus.OPENED
    }

    fun isOpened(): Boolean {
        return status == PositionStatus.OPENED
    }

    fun match(otherMineSweeperIndex: MineSweeperIndex): Boolean {
        return position.match(otherMineSweeperIndex.position)
    }

    companion object {
        const val MINE = -1
    }
}
