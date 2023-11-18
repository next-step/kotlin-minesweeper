package minesweeper.domain

class MineSweeperResult(private val mapPositions: List<Position>, private val mines: Mines) {
    val resultByRow get() = mapPositions.map {
        MineSweeperIndex(it).mineCount(mines) to mines.isMine(it)
    }
}
