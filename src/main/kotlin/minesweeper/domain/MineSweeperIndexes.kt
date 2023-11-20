package minesweeper.domain

class MineSweeperIndexes(val mineSweeperIndexes: List<MineSweeperIndex>) {

    fun open(mines: Mines, mineSweeperIndex: MineSweeperIndex): List<MineSweeperIndex> {
        val result = mutableListOf<MineSweeperIndex>()
        if (isOpen(mineSweeperIndex.position)) return result
        if (mines.isMine(mineSweeperIndex.position)) return result
        mineSweeperIndexes.find { it.position == mineSweeperIndex.position }?.let {
            it.status = PositionStatus.OPENED
            result.add(it)
        }
        if (mineSweeperIndex.mineCount(mines, this) != 0) return result
        IndexSquare.squareIndex(mineSweeperIndex.position, this).mineSweeperIndexes
            .filter { it.status == PositionStatus.CLOSED }
            .forEach {
                result.addAll(open(mines, it))
            }
        return result
    }

    private fun isOpen(position: Position): Boolean {
        return mineSweeperIndexes.find { it.position == position }?.status == PositionStatus.OPENED
    }
}
