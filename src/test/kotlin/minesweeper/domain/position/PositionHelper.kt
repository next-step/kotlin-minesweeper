package minesweeper.domain.position

import org.junit.jupiter.params.provider.Arguments

object PositionHelper {

    private fun makePositions(vararg positions: Position): Positions = Positions(positions.toList())

    fun getAroundMineQuantityTestData(): List<Arguments> = listOf(
        Arguments.of(
            Position(x = 1, y = 1),
            Positions(listOf(Position(x = 2, y = 2))),
            1,
        ),
        Arguments.of(
            Position(x = 1, y = 1),
            this.makePositions(
                Position(x = 1, y = 2),
                Position(x = 2, y = 2),
            ),
            2,
        ),
        Arguments.of(
            Position(x = 1, y = 1),
            this.makePositions(
                Position(x = 1, y = 2),
                Position(x = 2, y = 2),
                Position(x = 2, y = 1),
            ),
            3,
        ),
        Arguments.of(
            Position(x = 1, y = 1),
            this.makePositions(
                Position(x = 1, y = 2),
                Position(x = 2, y = 2),
                Position(x = 2, y = 1),
            ),
            3,
        ),
        Arguments.of(
            Position(x = 2, y = 2),
            this.makePositions(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
            ),
            4,
        ),
        Arguments.of(
            Position(x = 2, y = 2),
            this.makePositions(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
                Position(x = 2, y = 3),
            ),
            5,
        ),
        Arguments.of(
            Position(x = 2, y = 2),
            this.makePositions(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
                Position(x = 2, y = 3),
                Position(x = 3, y = 1),
                Position(x = 4, y = 1),
            ),
            6,
        ),
        Arguments.of(
            Position(x = 2, y = 2),
            this.makePositions(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
                Position(x = 2, y = 3),
                Position(x = 3, y = 1),
                Position(x = 3, y = 2),
                Position(x = 4, y = 1),
                Position(x = 4, y = 2),
            ),
            7,
        ),
        Arguments.of(
            Position(x = 2, y = 2),
            this.makePositions(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3),
                Position(x = 2, y = 1),
                Position(x = 2, y = 3),
                Position(x = 3, y = 1),
                Position(x = 3, y = 2),
                Position(x = 3, y = 3),
                Position(x = 4, y = 1),
                Position(x = 4, y = 2),
                Position(x = 4, y = 3),
            ),
            8,
        ),
    )
}
