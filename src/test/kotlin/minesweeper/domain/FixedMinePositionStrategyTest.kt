package minesweeper.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class FixedMinePositionStrategyTest {

    @ParameterizedTest
    @CsvSource(
        "4, 5",
        "6, 4",
        "-1, 0",
    )
    internal fun `지뢰 매설 위치가 지도의 내부가 아니면 예외가 발생한다`(
        valueX: Int,
        valueY: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            FixedMinePositionStrategy(
                mineMapConfig = MineMapConfig(
                    width = 5,
                    height = 5,
                    mineCount = 10
                ),
                minePositions = Positions(listOf(Position(valueX, valueY)))
            )
        }
    }
}
