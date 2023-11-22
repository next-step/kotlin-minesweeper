package minesweeper.domain

class MineSweeperResult(private val mineSweeperIndexes: MineSweeperMap) {
    val resultByRow
        get() = mineSweeperIndexes.map {
            IndexResult(it.mineCount(mineSweeperIndexes), it.isMine(), it.openStatus)
        }
}
