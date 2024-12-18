package minesweeper.domain.strategy

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location

interface LandmineLocationSelector {
    fun selectCandidates(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): List<Location>
}
