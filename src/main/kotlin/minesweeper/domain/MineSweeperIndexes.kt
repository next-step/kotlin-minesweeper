package minesweeper.domain

class MineSweeperIndexes(val mineSweeperIndexes: List<MineSweeperIndex>) {

    fun open(mines: Mines, mineSweeperIndex: MineSweeperIndex): MineStatus {
        MineSweeperValidator.validate(mineSweeperIndex, this)
        if (isOpen(mineSweeperIndex.position)) {
            return MineStatus.NOT_MINE
        }
        if (mines.isMine(mineSweeperIndex.position)) {
            return MineStatus.MINE
        }
        mineSweeperIndexes.find { it.position == mineSweeperIndex.position }?.open()
        if (mineSweeperIndex.mineCount(mines, this) != 0) {
            return MineStatus.NOT_MINE
        }

        findEmptyIndex(mineSweeperIndex, mines)
        return MineStatus.NOT_MINE
    }

    private fun findEmptyIndex(mineSweeperIndex: MineSweeperIndex, mines: Mines) {
        IndexSquare.squareIndex(mineSweeperIndex.position, this).mineSweeperIndexes
            .filter { it.status == PositionStatus.CLOSED }
            .forEach {
                open(mines, it)
            }
    }

    private fun isOpen(position: Position): Boolean {
        return mineSweeperIndexes.find { it.position == position }?.status == PositionStatus.OPENED
    }
}
