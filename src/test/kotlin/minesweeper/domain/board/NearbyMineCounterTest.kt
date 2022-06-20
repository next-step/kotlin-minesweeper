package minesweeper.domain.board

import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.domain.cell.Empty
import minesweeper.domain.common.PositiveInt
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
        numberOfMines: Int,
        expectedNumberOfNearbyMines: List<Int>
    ) {
        // given
        val mineBoard = mineBoard {
            width(width)
            height(height)
            numberOfMines(numberOfMines)
            mineStrategy(FirstIndicesMineStrategy().strategy())
        }

        // when
        val result = mineBoard.board.cells.map {
            if (it is Empty) it.numberOfNearbyMines else 0
        }

        // then
        Assertions.assertThat(result).containsExactlyElementsOf(expectedNumberOfNearbyMines)
    }

    companion object {
        @JvmStatic
        fun `임의 케이스`() = Stream.of(
            Arguments.of(3, 3, 1, listOf(0, 1, 0, 1, 1, 0, 0, 0, 0)),
            Arguments.of(3, 3, 2, listOf(0, 0, 1, 2, 2, 1, 0, 0, 0)),
            Arguments.of(3, 3, 3, listOf(0, 0, 0, 2, 3, 2, 0, 0, 0)),
            Arguments.of(3, 3, 4, listOf(0, 0, 0, 0, 4, 2, 1, 1, 0))
        )
    }

    inner class FirstIndicesMineStrategy : MineStrategy {
        override fun strategy(): (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int> {
            return { _, numberOfMines ->
                (0 until numberOfMines.value).toList()
            }
        }
    }
}
