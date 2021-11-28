package minesweeper.domain

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
}
