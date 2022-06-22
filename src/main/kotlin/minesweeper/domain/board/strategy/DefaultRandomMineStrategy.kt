package minesweeper.domain.board.strategy

class DefaultRandomMineStrategy : MineStrategy {

    override fun getMineIndices(numberOfCells: Int, numberOfMines: Int): List<Int> {
        return numberOfCells.toShuffledMineIndices(numberOfMines)
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines)
    }
}
