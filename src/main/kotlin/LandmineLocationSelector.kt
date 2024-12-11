class LandmineLocationSelector(
    private val shuffleAlgorithm: ShuffleAlgorithm = RandomShuffleAlgorithm(),
) {
    fun selectCandidates(
        grid: List<List<Cell>>,
        countOfLandmine: Int,
    ): List<Cell> {
        val allCells = grid.flatten()
        val shuffledCells = shuffleAlgorithm.shuffle(allCells)
        return shuffledCells.take(countOfLandmine)
    }
}
