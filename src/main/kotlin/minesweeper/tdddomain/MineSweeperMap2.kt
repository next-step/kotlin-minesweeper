package minesweeper.tdddomain

import minesweeper.domain.GameStatus
import minesweeper.domain.Position

class MineSweeperMap2(val mineSweeperIndexes: List<MineSweeperIndex2>) {

    fun open(position: Position): GameStatus {
        val mineSweeperIndex = mineSweeperIndexes.find { it.position == position } ?: throw IllegalArgumentException()
        mineSweeperIndex.open()
        if (mineSweeperIndex.isMine()) {
            return GameStatus.LOSE
        }
        return GameStatus.CONTINUE
    }
}
