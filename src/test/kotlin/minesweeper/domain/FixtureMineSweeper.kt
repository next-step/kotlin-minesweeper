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

    val mockPositions5x5 = MockPositions(GameBoardSize(5, 5).createPositions())

    val mockPositions2x2 = MockPositions(GameBoardSize(2, 2).createPositions())

    val customMinePosition5x5 = Positions(
        listOf(
            Position(0, 0),
            Position(1, 1),
            Position(2, 2),
            Position(3, 3),
            Position(4, 4)
        )
    )
}
