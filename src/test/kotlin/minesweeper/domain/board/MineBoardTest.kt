package minesweeper.domain.board

import io.mockk.boxedClass
import minesweeper.domain.board.random.DefaultRandomMineStrategy
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.div
import minesweeper.domain.common.rem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MineBoardTest {

    @ParameterizedTest
    @MethodSource("지뢰 보드의 높이와 너비 중 음수가 존재하는 케이스")
    fun `지뢰 보드의 높이와 너비는 음수일 수 없다`(width: Int, height: Int) {
        val exception = assertThrows<IllegalArgumentException> { newMineBoard(width, height, NUMBER_OF_MINES) }
        assertThat(exception.message).isEqualTo("property must be zero or positive.")
    }

    @ParameterizedTest
    @MethodSource("지뢰 개수가 지뢰 보드의 범위를 벗어나는 케이스")
    fun `지뢰 개수는 지뢰 보드의 사이즈 범위 내에 있어야 한다`(width: Int, height: Int, numberOfMines: Int) {
        val size = width * height
        val exception = assertThrows<IllegalArgumentException> { newMineBoard(width, height, numberOfMines) }
        assertThat(exception.message).isEqualTo("number of mines must be within range of 0 ~ $size")
    }

    @Test
    fun `지뢰 보드는 지뢰와 빈 공간으로 모두 채워진다`() {
        // given
        val expectedNumberOfEmpty = WIDTH * HEIGHT - NUMBER_OF_MINES

        // when
        val mineBoard = newMineBoard(WIDTH, HEIGHT, NUMBER_OF_MINES)
        val countOfEmpty = mineBoard.board.cells.count { it is Empty }
        val countOfMine = mineBoard.board.cells.count { it is Mine }

        // then
        assertThat(countOfEmpty).isEqualTo(expectedNumberOfEmpty)
        assertThat(countOfMine).isEqualTo(NUMBER_OF_MINES)
    }

    @Test
    fun `지뢰 보드를 생성할 때 지뢰의 위치를 결정한다`() {
        // given
        val width = PositiveInt(WIDTH)
        val height = PositiveInt(HEIGHT)
        val numberOfMines = PositiveInt(NUMBER_OF_MINES)
        val strategy = DefaultRandomMineStrategy().strategy()

        // when
        val mineBoard = MineBoard(width, height, numberOfMines, strategy)

        // then
        mineBoard.mineIndices.forEach { index ->
            val x = index % width
            val y = index / width
            val cell = mineBoard.board.cells[index]
            assertThat(cell.boxedClass()).isEqualTo(Mine::class)
            assertThat(cell).extracting("x", "y").containsExactly(x, y)
        }
    }

    companion object {
        private const val WIDTH = 10
        private const val HEIGHT = 10
        private const val NUMBER_OF_MINES = 10

        private fun newMineBoard(width: Int, height: Int, numberOfMines: Int) = mineBoard {
            width(width)
            height(height)
            numberOfMines(numberOfMines)
            mineStrategy(DefaultRandomMineStrategy().strategy())
        }

        @JvmStatic
        fun `지뢰 보드의 높이와 너비 중 음수가 존재하는 케이스`() = Stream.of(
            Arguments.of(-10, 10),
            Arguments.of(10, -10),
            Arguments.of(-10, -10)
        )

        @JvmStatic
        fun `지뢰 개수가 지뢰 보드의 범위를 벗어나는 케이스`() = Stream.of(
            Arguments.of(1, 1, 2),
            Arguments.of(5, 5, 50),
            Arguments.of(10, 10, 101),
        )
    }
}
