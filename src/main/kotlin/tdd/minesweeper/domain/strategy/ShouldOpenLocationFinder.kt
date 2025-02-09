package tdd.minesweeper.domain.strategy

import tdd.minesweeper.domain.Board
import tdd.minesweeper.domain.Location

interface ShouldOpenLocationFinder {
    fun findAllShouldOpen(
        board: Board,
        location: Location,
    ): Set<Location>
}
