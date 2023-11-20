package minesweeper.domain

class MineSweeperResult(private val mineSweeperIndexes: MineSweeperIndexes, private val mines: Mines) {
    val resultByRow get() = mineSweeperIndexes.mineSweeperIndexes.map { mineSweeperIndex ->
        IndexResult(mineSweeperIndex.mineCount(mines, mineSweeperIndexes), mines.isMine(mineSweeperIndex.position))
    }
}
