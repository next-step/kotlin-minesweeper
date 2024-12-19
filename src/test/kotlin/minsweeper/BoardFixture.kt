package minsweeper

import minsweeper.domain.*

// 0 1 *
// 0 1 1
// 0 0 0
fun getFixtureBoard(): Board {
    return Board.of(
        BoardSize(3, 3),
        BoardLines(
            listOf(
                BoardLine(listOf(Cell.Island(0), Cell.Island(1), Cell.Mine)),
                BoardLine(listOf(Cell.Island(0), Cell.Island(1), Cell.Island(1))),
                BoardLine(listOf(Cell.Island(0), Cell.Island(0), Cell.Island(0))),
            )
        ),
    )
}