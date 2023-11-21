package minesweeper.domain

object MineGenerator {

    fun generate(mineSweeperIndexes: MineSweeperIndexes, mineCount: Int): Mines {
        require(mineSweeperIndexes.mineSweeperIndexes.size >= mineCount) { ERROR_MESSAGE }
        val mines = mineSweeperIndexes.mineSweeperIndexes
            .map { Mine(it.position) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }

    private const val ERROR_MESSAGE = "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
}
