package minesweeper.domain

class DefaultLandmineLocationSelector(
    private val shuffleAlgorithm: ShuffleAlgorithm = RandomShuffleAlgorithm(),
) : LandmineLocationSelector {
    override fun selectCandidates(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): List<Location> {
        val allCells = board.rows.flatMap { it.cells() }
        val shuffledCells = shuffleAlgorithm.shuffle(allCells)
        return shuffledCells
            .take(countOfLandmines.value)
            .map { it.location() }
    }
}
