package minesweeper.domain

class DefaultLandmineLocationSelector(
    private val shuffleAlgorithm: ShuffleAlgorithm = RandomShuffleAlgorithm(),
) : LandmineLocationSelector {
    override fun selectCandidates(
        board: GameBoard,
        countOfLandmines: Int,
    ): List<Location> {
        val allCells = board.grid.flatten()
        val shuffledCells = shuffleAlgorithm.shuffle(allCells)
        return shuffledCells.take(countOfLandmines).map { it.location() }
    }
}
