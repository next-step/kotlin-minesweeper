package minesweeper.tdddomain

import minesweeper.domain.GameStatus
import minesweeper.domain.IndexSquare
import minesweeper.domain.Position
import minesweeper.domain.PositionStatus

class MineSweeperMap2(val mineSweeperIndexes: List<MineSweeperIndex2>) :
    Collection<MineSweeperIndex2> by mineSweeperIndexes {

    constructor(vararg mineSweeperIndexes: MineSweeperIndex2) : this(mineSweeperIndexes.toList())

    fun open(position: Position): GameStatus {
        val mineSweeperIndex = mineSweeperIndexes.find { it.position == position } ?: throw IllegalArgumentException()
        if (mineSweeperIndex.isMine()) {
            return GameStatus.LOSE
        }

        mineSweeperIndex.open()
        if (mineSweeperIndex.mineCount() != 0) {
            return GameStatus.CONTINUE
        }

        emptyIndex(mineSweeperIndex)
        return GameStatus.CONTINUE
    }

    private fun emptyIndex(mineSweeperIndex: MineSweeperIndex2) {
        IndexSquare.squareIndex(mineSweeperIndex, this)
            .filter { it.openStatus == PositionStatus.CLOSED }
            .forEach {
                open(it.position)
            }
    }

    private fun MineSweeperIndex2.mineCount(): Int {
        return mineCount(this@MineSweeperMap2)
    }
}
