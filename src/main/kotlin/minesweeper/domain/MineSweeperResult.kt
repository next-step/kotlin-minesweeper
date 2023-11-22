package minesweeper.domain

import minesweeper.tdddomain.MineSweeperMap2

class MineSweeperResult(private val mineSweeperIndexes: MineSweeperMap2) {
    val resultByRow
        get() = mineSweeperIndexes.map {
            IndexResult(it.mineCount(mineSweeperIndexes), it.isMine(), it.openStatus)
        }
}
