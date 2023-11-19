package minesweeper.domain

object MineGenerator {

    fun generate(positions: List<Position>, mineCount: Int): Mines {
        require(positions.size >= mineCount) { ERROR_MESSAGE }
        val copyPosition = positions.map { it.copy() }
        val mines = copyPosition
            .map { Mine(it) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }

    private const val ERROR_MESSAGE = "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
}
