package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    @Test
    fun `Cell 중에 가장 Column이 큰 값을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        val actual = cells.maxColumnOrNull()
        assertThat(actual).isEqualTo(Column(3))
    }

    @Test
    fun `Cell 중에 가장 Row가 큰 값을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        val actual = cells.maxRowOrNull()
        assertThat(actual).isEqualTo(Row(2))
    }

    @Test
    fun `Row, Column에 해당하는 Cell을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        assertAll(
            { assertThat(cells[1 to 1]).isNotNull },
            { assertThat(cells[1 to 10]).isNull() },
        )
    }

    @Test
    fun `Position의 Cell을 Mine으로 변환할 수 있다`() {
        // given
        val cells = Cells(1 to 1, 1 to 2)

        // when
        val actual = cells.mine(Position(1, 1))

        // then
        assertAll(
            {
                assertThat(actual[Position(1, 1)])
                    .isEqualTo(Cell.Mine(Position(1, 1)))
            },
            {
                assertThat(actual[Position(1, 2)])
                    .isEqualTo(Cell.zero(Position(1, 2)))
            },
        )
    }

    @Test
    fun `Position의 Cell을 열 수 있다`() {
        // given
        val position = Position(1, 1)
        val cells = Cells(1 to 1)

        // when
        val actual = cells.tryOpen(position)[position]?.isVisible

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `Position이 Zero Cell이면 주변의 모든 Cell을 연다`() {
        // given (3X3)
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3, 3 to 1, 3 to 2, 3 to 3)

        // when
        val actual = cells.tryOpen(Position(2, 2))

        // then
        assertAll(
            { assertThat(actual[1 to 1]!!.isVisible).isTrue },
            { assertThat(actual[1 to 2]!!.isVisible).isTrue },
            { assertThat(actual[1 to 3]!!.isVisible).isTrue },
            { assertThat(actual[2 to 1]!!.isVisible).isTrue },
            { assertThat(actual[2 to 2]!!.isVisible).isTrue },
            { assertThat(actual[2 to 3]!!.isVisible).isTrue },
            { assertThat(actual[3 to 1]!!.isVisible).isTrue },
            { assertThat(actual[3 to 2]!!.isVisible).isTrue },
            { assertThat(actual[3 to 3]!!.isVisible).isTrue },
        )
    }
}
