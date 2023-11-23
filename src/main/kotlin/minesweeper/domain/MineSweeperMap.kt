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

        findEmptyIndex(mineSweeperIndex)
        return GameStatus.CONTINUE
    }

    private fun findEmptyIndex(mineSweeperIndex: MineSweeperIndex) {
        findIndexSquare(mineSweeperIndex)
            .filter { it.openStatus == PositionStatus.CLOSED }
            .forEach {
                open(it.position)
            }
    }

    fun findIndexSquare(mineSweeperIndex: MineSweeperIndex): List<MineSweeperIndex> {
        val squares = filterSquare(mineSweeperIndex).flatMap { indexSquare ->
            mineSweeperIndexes.filter {
                it.position == mineSweeperIndex.position + indexSquare.position
            }
        }
        return squares
    }

    private fun filterSquare(mineSweeperIndex: MineSweeperIndex): List<IndexSquare> {
        return IndexSquare.values().filter { indexSquare ->
            mineSweeperIndex.position + indexSquare.position in mineSweeperIndexes.map { it.position }
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
