package minesweeper.domain

class MineSweeperMap(val mineSweeperIndexes: List<MineSweeperIndex>) :
    Collection<MineSweeperIndex> by mineSweeperIndexes {

    constructor(vararg mineSweeperIndexes: MineSweeperIndex) : this(mineSweeperIndexes.toList())

    fun open(position: Position): GameStatus {
        val mineSweeperIndex = findIndex(position)
        if (mineSweeperIndex.isMine()) {
            return GameStatus.LOSE
        }

        mineSweeperIndex.open()
        if (mineSweeperIndex.mineCount() != NO_MINE_COUNT) {
            return GameStatus.CONTINUE
        }

        emptyIndex(mineSweeperIndex)
        return GameStatus.CONTINUE
    }

    private fun emptyIndex(mineSweeperIndex: MineSweeperIndex) {
        IndexSquare.squareIndex(mineSweeperIndex, this)
            .filter { it.openStatus == PositionStatus.CLOSED }
            .forEach {
                open(it.position)
            }
    }

    private fun MineSweeperIndex.mineCount(): Int {
        return mineCount(this@MineSweeperMap)
    }

    private fun findIndex(position: Position): MineSweeperIndex {
        return mineSweeperIndexes.find { it.position == position } ?: throw IllegalArgumentException(
            MINE_SCOPE_ERROR_MESSAGE
        )
    }

    companion object {
        private const val MINE_SCOPE_ERROR_MESSAGE = "지뢰 찾기 범위를 벗어났습니다."
        private const val NO_MINE_COUNT = 0
    }
}
