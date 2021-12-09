package minesweeper.domain.position

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class PositionTest {

    @Test
    fun `Position은 Row가 다르면 Row의 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(3, 1),
            Position.from(2, 1),
        )

        val result = positions.sorted()

        assertThat(result.map { it.row.value }).isEqualTo(listOf(1, 2, 3))
    }

    @Test
    fun `Position은 Row가 같으면 Column의 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(1, 3),
            Position.from(1, 2),
        )

        val result = positions.sorted()

        assertThat(result.map { it.column.value }).isEqualTo(listOf(1, 2, 3))
    }

    @Test
    fun `Position은 Row, Column 순으로 정렬된다`() {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(2, 3),
            Position.from(1, 3),
            Position.from(2, 1),
            Position.from(1, 2),
        )

        val result = positions.sorted()

        assertThat(result).isEqualTo(
            listOf(
                Position.from(1, 1),
                Position.from(1, 2),
                Position.from(1, 3),
                Position.from(2, 1),
                Position.from(2, 3),
            )
        )
    }

    /**
     *   C  C  C
     *   C  T  C
     *   C  C  C
     */
    @Test
    fun `자신을 둘러싼 Positions를 구한다 - 주위 모두 존재`() {
        val position = Position.from(row = 2, column = 2)

        val result = position.around()

        assertThat(result.positions).containsExactlyInAnyOrder(
            Position.from(row = 1, column = 1),
            Position.from(row = 1, column = 2),
            Position.from(row = 1, column = 3),
            Position.from(row = 2, column = 1),
            Position.from(row = 2, column = 3),
            Position.from(row = 3, column = 1),
            Position.from(row = 3, column = 2),
            Position.from(row = 3, column = 3),
        )
    }

    /**
     *   C  C
     *   T  C
     *   C  C
     */
    @Test
    fun `자신을 둘러싼 Positions를 구한다 - 주위 일부 존재`() {
        val position = Position.from(row = 2, column = 1)

        val result = position.around()

        assertThat(result.positions).containsExactlyInAnyOrder(
            Position.from(row = 1, column = 1),
            Position.from(row = 1, column = 2),
            Position.from(row = 2, column = 2),
            Position.from(row = 3, column = 1),
            Position.from(row = 3, column = 2),
        )
    }
}
