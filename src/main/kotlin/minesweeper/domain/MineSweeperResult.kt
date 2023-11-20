package minesweeper.domain

class MineSweeperResult(private val mapPositions: List<Position>, private val mines: Mines) {
    val resultByRow get() = mapPositions.map {
        IndexResult(MineSweeperIndex(it).mineCount(mines), mines.isMine(it))
    }
}
