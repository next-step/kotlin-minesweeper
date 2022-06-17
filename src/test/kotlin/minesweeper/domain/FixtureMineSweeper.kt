package minesweeper.domain

object FixtureMineSweeper {
    val positions2x2 = Positions(
        listOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1)
        )
    )

    val positions10x10 = Positions(GameBoardSize(10, 10).createPositions())
}
