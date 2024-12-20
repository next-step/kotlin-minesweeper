package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `지뢰 최소개수시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board(Dimensions(3, 3), emptySet())
        }
    }

    @Test
    fun `지뢰 최대개수 초과시 예외 발생`() {
        assertThatIllegalArgumentException().isThrownBy {
            Board(Dimensions(2, 2), setOf(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1), Position(2, 1)))
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
