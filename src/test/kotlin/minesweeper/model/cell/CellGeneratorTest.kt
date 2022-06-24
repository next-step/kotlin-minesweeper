package minesweeper.model.cell

import minesweeper.fixture.toCellGenerator
import minesweeper.model.coordinate.Coordinate
import minesweeper.model.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CellGeneratorTest {

    @Test
    fun `지뢰 생성 테스트`() {
        val mineCells = listOf(
            "*--",
            "---"
        )
        val cellGenerator = mineCells.toCellGenerator()
        val safeCell00 = cellGenerator.createCell(Position(0, 0))
        val safeCell01 = cellGenerator.createCell(Position(0, 1))
        val safeCell02 = cellGenerator.createCell(Position(0, 2))

        val safeCell10 = cellGenerator.createCell(Position(1, 0))
        val safeCell11 = cellGenerator.createCell(Position(1, 1))
        val safeCell12 = cellGenerator.createCell(Position(1, 2))

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
        val cellGenerator = mineCells.toCellGenerator()
        val actualCell = cellGenerator.createCell(Position(1, 1))

        assertAll(
            { assertThat(actualCell).isInstanceOf(Cell.Safe::class.java) },
            {
                assertThat((actualCell as Cell.Safe).surroundMineCount)
                    .isEqualTo(8)
            }
        )
    }

    @ParameterizedTest
    @MethodSource("provideCellGeneratorAndPosition")
    fun `주변 지뢰 갯수 테스트 다중 케이스`(
        cellGenerator: CellGenerator,
        position: Position,
        expectedMineCount: Int
    ) {

        val actualCell = cellGenerator.createCell(position)

        assertAll(
            { assertThat(actualCell).isInstanceOf(Cell.Safe::class.java) },
            {
                assertThat((actualCell as Cell.Safe).surroundMineCount).isEqualTo(expectedMineCount)
            }
        )
    }

    companion object {

        private fun CellGenerator.createCell(coordinate: Coordinate) =
            this.createCell(coordinate, Position(-1, -1))

        @JvmStatic
        fun provideCellGeneratorAndPosition(): Stream<Arguments> {

            return Stream.of(
                Arguments.of(
                    listOf(
                        "-**",
                        "***",
                        "***"
                    ).toCellGenerator(),
                    Position(0, 0),
                    3
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "-**",
                        "***"
                    ).toCellGenerator(),
                    Position(1, 0),
                    5
                ),

                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "-**"
                    ).toCellGenerator(),
                    Position(2, 0),
                    3
                ),
                Arguments.of(
                    listOf(
                        "---",
                        "---",
                        "---"
                    ).toCellGenerator(),
                    Position(1, 1),
                    0
                ),
                Arguments.of(
                    listOf(
                        "**-",
                        "***",
                        "***"
                    ).toCellGenerator(),
                    Position(0, 2),
                    3
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "**-",
                        "***"
                    ).toCellGenerator(),
                    Position(1, 2),
                    5
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "**-"
                    ).toCellGenerator(),
                    Position(2, 2),
                    3
                ),
                Arguments.of(
                    listOf(
                        "*-*",
                        "***",
                        "***"
                    ).toCellGenerator(),
                    Position(0, 1),
                    5
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "*-*",
                        "***"
                    ).toCellGenerator(),
                    Position(1, 1),
                    8
                ),
                Arguments.of(
                    listOf(
                        "***",
                        "***",
                        "*-*"
                    ).toCellGenerator(),
                    Position(2, 1),
                    5
                )
            )
        }
    }
}
