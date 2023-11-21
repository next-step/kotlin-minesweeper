package minesweeper.domain

class MineSweeperIndexes(val mineSweeperIndexes: List<MineSweeperIndex>) {

    fun open(mines: Mines, mineSweeperIndex: MineSweeperIndex): MineStatus {
        MineSweeperValidator.validate(mineSweeperIndex, this)
        val findIndex = findIndex(mineSweeperIndex)
        return checkMine(findIndex, mines)
    }

    private fun checkMine(mineSweeperIndex: MineSweeperIndex, mines: Mines): MineStatus {
        if (mineSweeperIndex.isOpened()) {
            return MineStatus.NOT_MINE
        }
        if (mines.isMine(mineSweeperIndex)) {
            return MineStatus.MINE
        }
        mineSweeperIndex.open()
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

    private fun findIndex(index: MineSweeperIndex): MineSweeperIndex {
        return mineSweeperIndexes.find { it.match(index) } ?: throw IllegalArgumentException("해당 인덱스가 없습니다.")
    }
}
