package minesweeper.domain

class MineGame(private val cellGenerator: RandomCellsGenerator) {
    fun createBoard(height: PositiveNumber, width: PositiveNumber, mineCount: PositiveNumber): MineBoard {
        val randomCells = cellGenerator.generate(
            totalCount = height * width,
            mineCount = mineCount
        )
        return MineBoard.generate(width, randomCells)
    }
}
