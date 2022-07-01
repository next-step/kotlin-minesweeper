package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CellTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 9, 100])
    fun `근처 칸의 지뢰 개수는 0부터 8까지만 허용한다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            Cell.Land.from(input)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7])
    fun `increaseMineCount를 통해 현재 칸의 근처 지뢰 개수를 늘릴 수 있다`(input: Int) {
        val cell = Cell.Land.from(input)
        val expected = Cell.Land.from(input + 1)

        assertThat(cell.increaseMineCount()).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [8])
    fun `근처 지뢰 개수가 8개를 넘어갈 수는 없다`(input: Int) {
        val cell = Cell.Land.from(input)

        assertThat(cell.increaseMineCount()).isEqualTo(Cell.Land.EIGHT)
    }
}
