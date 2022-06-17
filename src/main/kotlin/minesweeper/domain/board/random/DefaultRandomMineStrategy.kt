package minesweeper.domain.board.random

class DefaultRandomMineStrategy : RandomMineStrategy {

    override fun strategy(): (numberOfCells: Int, numberOfMines: Int) -> List<Int> {
        return { numberOfCells, numberOfMines ->
            numberOfCells.toShuffledMineIndices(numberOfMines)
        }
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines)
    }
}
