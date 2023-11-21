package minesweeper.domain

class MineSweeperResult(private val mineSweeperIndexes: MineSweeperIndexes, private val mines: Mines) {
    val resultByRow
        get() = mineSweeperIndexes.mineSweeperIndexes.map {
            IndexResult(it.mineCount(mines, mineSweeperIndexes), mines.isMine(it), it.status)
        }
}
