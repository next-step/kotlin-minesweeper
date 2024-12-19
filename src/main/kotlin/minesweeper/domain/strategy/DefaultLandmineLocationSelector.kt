package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.Location

class DefaultLandmineLocationSelector(
    private val shuffleAlgorithm: ShuffleAlgorithm = RandomShuffleAlgorithm(),
) : LandmineLocationSelector {
    override fun selectCandidates(
        cells: Cells,
        countOfLandmines: CountOfLandmines,
    ): List<Location> {
        val shuffledCells = shuffleAlgorithm.shuffle(cells)
        return shuffledCells
            .take(countOfLandmines.value)
            .map { it.location }
    }
}
