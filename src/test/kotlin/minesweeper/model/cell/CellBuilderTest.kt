package minesweeper.model.cell

import minesweeper.fixture.toCellBuilder
import minesweeper.model.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CellBuilderTest {

    @Test
    fun `지뢰 생성 테스트`() {
        val mineCells = listOf(
            "*--",
            "---"
        )
        val cellBuilder = mineCells.toCellBuilder()
        val safeCell00 = cellBuilder.createCell(Position(0, 0))
        val safeCell01 = cellBuilder.createCell(Position(0, 1))
        val safeCell02 = cellBuilder.createCell(Position(0, 2))

        val safeCell10 = cellBuilder.createCell(Position(1, 0))
        val safeCell11 = cellBuilder.createCell(Position(1, 1))
        val safeCell12 = cellBuilder.createCell(Position(1, 2))

        assertAll(
            { assertThat(safeCell00).isInstanceOf(Cell.Mine::class.java) },
            { assertThat(safeCell01).isInstanceOf(Cell.Safe::class.java) },
            { assertThat(safeCell02).isInstanceOf(Cell.Safe::class.java) },

            { assertThat(safeCell10).isInstanceOf(Cell.Safe::class.java) },
            { assertThat(safeCell11).isInstanceOf(Cell.Safe::class.java) },
            { assertThat(safeCell12).isInstanceOf(Cell.Safe::class.java) }
        )
    }

    @Test
    fun `주변 지뢰 갯수 테스트`() {
        val mineCells = listOf(
            "***",
            "*-*",
            "***"
        )
        val cellBuilder = mineCells.toCellBuilder()
        val actualCell = cellBuilder.createCell(Position(1, 1))

        assertAll(
            { assertThat(actualCell).isInstanceOf(Cell.Safe::class.java) },
            {
                assertThat((actualCell as Cell.Safe).surroundMineCount)
                    .isEqualTo(8)
            }
        )
    }

    @ParameterizedTest
    @MethodSource("provideCellBuilderAndPosition")
    fun `주변 지뢰 갯수 테스트 다중 케이스`(
        cellBuilder: CellBuilder,
        position: Position,
        expectedMineCount: Int
    ) {

        val actualCell = cellBuilder.createCell(position)

        assertAll(
            { assertThat(actualCell).isInstanceOf(Cell.Safe::class.java) },
            {
                assertThat((actualCell as Cell.Safe).surroundMineCount).isEqualTo(expectedMineCount)
            }
        )
    }

    companion object {

        @JvmStatic
        fun provideCellBuilderAndPosition(): Stream<Arguments> {

            return Stream.of(
                Arguments.of(
                    listOf(
                        "-**",
                        "***",
                        "***"
                    ).toCellBuilder(),
                    Position(0, 0),
                    3
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "-**",
                        "***"
                    ).toCellBuilder(),
                    Position(1, 0),
                    5
                ),

                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "-**"
                    ).toCellBuilder(),
                    Position(2, 0),
                    3
                ),
                Arguments.of(
                    listOf(
                        "---",
                        "---",
                        "---"
                    ).toCellBuilder(),
                    Position(1, 1),
                    0
                ),
                Arguments.of(
                    listOf(
                        "**-",
                        "***",
                        "***"
                    ).toCellBuilder(),
                    Position(0, 2),
                    3
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "**-",
                        "***"
                    ).toCellBuilder(),
                    Position(1, 2),
                    5
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "**-"
                    ).toCellBuilder(),
                    Position(2, 2),
                    3
                ),
                Arguments.of(
                    listOf(
                        "*-*",
                        "***",
                        "***"
                    ).toCellBuilder(),
                    Position(0, 1),
                    5
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "*-*",
                        "***"
                    ).toCellBuilder(),
                    Position(1, 1),
                    8
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "*-*"
                    ).toCellBuilder(),
                    Position(2, 1),
                    5
                )
            )
        }
    }
}
