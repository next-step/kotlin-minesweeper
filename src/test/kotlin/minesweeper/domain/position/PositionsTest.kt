package minesweeper.domain.position

import minesweeper.domain.board.Height
import minesweeper.domain.board.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
class PositionsTest {

    @Test
    fun `height와 width가 주어지면, height를 row로 width를 column으로 Positions를 만든다`() {
        val result = Positions.from(Height(10), Width(5))

        val rows = result.positions.map { it.row.value }.distinct()
        val columns = result.positions.map { it.column.value }.distinct()
        assertAll(
            { assertThat(result.positions).hasSize(10 * 5) },
            { assertThat(rows).containsExactlyElementsOf(1..10) },
            { assertThat(columns).containsExactlyElementsOf(1..5) },
        )
    }

    @Test
    fun `row와 column의 범위가 주어지면 Positions를 만든다`() {
        val result = Positions.from(rowRange = 1..10, columnRange = 1..5)

        val rows = result.positions.map { it.row.value }.distinct()
        val columns = result.positions.map { it.column.value }.distinct()
        assertAll(
            { assertThat(result.positions).hasSize(10 * 5) },
            { assertThat(rows).containsExactlyElementsOf(1..10) },
            { assertThat(columns).containsExactlyElementsOf(1..5) },
        )
    }

    @Test
    fun `row 또는 column의 범위가 유효하지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Positions.from(rowRange = 0..10, columnRange = 1..5)
        }
    }

    @Test
    fun `index들이 주어지면 새로운 Positions를 구한다`() {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(2, 1),
            Position.from(3, 1),
            Position.from(4, 1),
            Position.from(5, 1),
        )
        val given = Positions(positions)
        val indexes = listOf(1, 3)

        val result = given.slice(indexes)

        assertThat(result.positions).isEqualTo(listOf(positions[1], positions[3]))
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 5])
    fun `index가 Positions의 범위를 벗어나면 예외가 발생한다`(index: Int) {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(2, 1),
            Position.from(3, 1),
            Position.from(4, 1),
            Position.from(5, 1),
        )
        val given = Positions(positions)
        val indexes = listOf(index)

        assertThrows<IllegalArgumentException> {
            given.slice(indexes)
        }
    }

    @Test
    fun `다른 Positions가 주어지면 자신의 Position들이 다른 것에 포함되는지 여부의 Map을 반환한다`() {
        val positions = listOf(
            Position.from(1, 1),
            Position.from(2, 1),
            Position.from(3, 1),
            Position.from(4, 1),
            Position.from(5, 1),
        )
        val given = Positions(positions)
        val other = Positions(
            listOf(
                Position.from(5, 1),
                Position.from(1, 1),
                Position.from(6, 1),
            )
        )

        val result = given.mapToIsIn(other)

        assertAll(
            { assertThat(result[positions[0]]).isTrue },
            { assertThat(result[positions[1]]).isFalse },
            { assertThat(result[positions[2]]).isFalse },
            { assertThat(result[positions[3]]).isFalse },
            { assertThat(result[positions[4]]).isTrue },
        )
    }
}
