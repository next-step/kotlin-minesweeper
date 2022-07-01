package minesweeper.domain.board

import minesweeper.domain.cell.Cell.Empty
import minesweeper.domain.cell.Cell.Mine
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.NearbyMineCounter
import minesweeper.domain.cell.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class NearbyMineCounterTest {

    @ParameterizedTest(name = "width:{0}, height:{1}, numberOfMines:{2}, expected:{3}")
    @MethodSource("임의 케이스")
    fun `각 셀에 표시될 숫자는 자신을 제외한 주변 8개 셀에 포함된 지뢰의 개수이다`(
        width: Int,
        height: Int,
        cellsRequest: List<Int>,
        expectedNumberOfNearbyMines: List<Int>
    ) {
        // given
        val cells = createCells(width, height, cellsRequest)

        // when
        NearbyMineCounter.count(cells)
        val result = cells.getNumberOfNearbyMines()

        // then
        Assertions.assertThat(result).containsExactlyElementsOf(expectedNumberOfNearbyMines)
    }

    private fun createCells(width: Int, height: Int, cellsRequest: List<Int>) = Cells(
        cellsRequest.mapIndexed { index, cell ->
            val x = index % width
            val y = index / width
            val position = Position(x, y)
            if (cell == 1) {
                Mine(position)
            } else {
                Empty(position)
            }
        }
    )

    private fun Cells.getNumberOfNearbyMines() = this.map {
        if (it is Empty) {
            it.numberOfNearbyMines
        } else {
            0
        }
    }

    companion object {
        @JvmStatic
        fun `임의 케이스`() = Stream.of(
            Arguments.of(
                2, 3,
                listOf(
                    0, 1,
                    0, 0,
                    0, 1
                ),
                listOf(
                    1, 0,
                    2, 2,
                    1, 0
                )
            ),
            Arguments.of(
                3, 2,
                listOf(
                    0, 1, 0,
                    0, 0, 1
                ),
                listOf(
                    1, 0, 2,
                    1, 2, 0
                )
            ),
            Arguments.of(
                3, 3,
                listOf(
                    0, 0, 1,
                    0, 0, 0,
                    1, 0, 0
                ),
                listOf(
                    0, 1, 0,
                    1, 2, 1,
                    0, 1, 0
                )
            ),
            Arguments.of(
                3, 3,
                listOf(
                    0, 0, 1,
                    0, 0, 0,
                    1, 0, 1
                ),
                listOf(
                    0, 1, 0,
                    1, 3, 2,
                    0, 2, 0
                )
            ),
            Arguments.of(
                3, 3,
                listOf(
                    0, 0, 0,
                    1, 0, 1,
                    0, 0, 0
                ),
                listOf(
                    1, 2, 1,
                    0, 2, 0,
                    1, 2, 1
                )
            )
        )
    }
}
