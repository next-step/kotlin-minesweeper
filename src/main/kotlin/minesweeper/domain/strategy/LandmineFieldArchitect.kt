package minesweeper.domain.strategy

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard

interface LandmineFieldArchitect {
    fun design(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): GameBoard
}
