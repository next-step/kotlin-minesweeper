package minesweeper.domain.strategy

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location

class DefaultLandmineLocationSelector(
    private val shuffleAlgorithm: ShuffleAlgorithm = RandomShuffleAlgorithm(),
) : LandmineLocationSelector {
    override fun selectCandidates(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): List<Location> {
        val allCells = board.allCells()
        val shuffledCells = shuffleAlgorithm.shuffle(allCells)
        return shuffledCells
            .take(countOfLandmines.value)
            .map { it.location() }
    }
}
