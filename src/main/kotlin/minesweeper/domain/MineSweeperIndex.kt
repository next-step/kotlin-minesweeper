package minesweeper.domain

class MineSweeperIndex(val position: Position, var status: PositionStatus = PositionStatus.CLOSED) {

    fun mineCount(mines: Mines, mineSweeperIndexes: MineSweeperIndexes): Int {
        if (mines.isMine(position)) return MINE
        return IndexSquare.squareIndex(position, mineSweeperIndexes).mineSweeperIndexes
            .count { mines.isMine(it.position) }
    }

    fun open() {
        status = PositionStatus.OPENED
    }

    companion object {
        const val MINE = -1
    }
}
