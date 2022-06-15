package minesweeper.domain.board

import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MineBoardTest {

    @ParameterizedTest
    @MethodSource("지뢰 보드의 높이, 너비, 지뢰 개수 중 음수가 존재하는 케이스")
    fun `지뢰 보드의 높이와 너비, 지뢰 개수는 음수일 수 없다`(width: Int, height: Int, numberOfMines: Int) {
        assertThatThrownBy { MineBoard.of(width, height, numberOfMines) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("width, height and number of mines must be positive.")
    }

    @ParameterizedTest
    @MethodSource("지뢰 개수가 지뢰 보드의 사이즈를 초과하는 케이스")
    fun `지뢰 보드의 사이즈를 초과하는 지뢰 개수를 가질 수 없다`(width: Int, height: Int, numberOfMines: Int) {
        assertThatThrownBy { MineBoard.of(width, height, numberOfMines) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("number of mines can't exceed the size of board.")
    }

    @Test
    fun `지뢰 보드는 지뢰와 빈 공간으로 모두 채워진다`() {
        // given
        val width = 2
        val height = 5
        val numberOfMines = 5
        val expectedNumberOfEmpty = width * height - numberOfMines

        // when
        val mineBoard = MineBoard.of(width, height, numberOfMines)
        val countOfEmpty = mineBoard.board.count { it is Empty }
        val countOfMine = mineBoard.board.count { it is Mine }

        // then
        assertThat(countOfEmpty).isEqualTo(expectedNumberOfEmpty)
        assertThat(countOfMine).isEqualTo(numberOfMines)
    }

    companion object {
        @JvmStatic
        fun `지뢰 보드의 높이, 너비, 지뢰 개수 중 음수가 존재하는 케이스`() = Stream.of(
            Arguments.of(-10, 10, 10),
            Arguments.of(10, -10, 10),
            Arguments.of(10, 10, -10),
            Arguments.of(-10, -10, 10),
            Arguments.of(10, -10, -10),
            Arguments.of(-10, 10, -10),
            Arguments.of(-10, -10, -10)
        )

        @JvmStatic
        fun `지뢰 개수가 지뢰 보드의 사이즈를 초과하는 케이스`() = Stream.of(
            Arguments.of(1, 1, 2),
            Arguments.of(5, 5, 50),
            Arguments.of(10, 10, 101),
        )
    }
}
