package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class Board2Test {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val dimensions = Dimensions(3, 3)
        val board = Board2(dimensions, setOf(Position(0, 0), Position(0, 2)))

        assertAll(
            { assertThat(board.checkMine(Position(0, 0))).isTrue() },
            { assertThat(board.checkMine(Position(0, 1))).isFalse() },
            { assertThat(board.checkMine(Position(0, 2))).isTrue() },
        )
    }

    @Test
    fun `지뢰 최소개수시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board2(Dimensions(3, 3), emptySet())
        }
    }

    @Test
    fun `지뢰 최대개수 초과시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board2(Dimensions(2, 2), setOf(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1), Position(2, 1)))
        }
    }
}

class MinePlacerTest {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val dimensions = Dimensions(3, 3)
        val minePlacer = MinePlacer(dimensions, 2)

        val minePositions = minePlacer.placeMines()

        assertThat(minePositions).hasSize(2)
    }
}
