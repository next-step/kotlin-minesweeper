package minesweeper.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @ParameterizedTest
    @CsvSource(
        "0 0",
        "1 0",
        "0 1",
        "-1 -1"
    )
    fun `Position 프로퍼티가 0이거나 음수라면 IllegalArgumentException이 발생한다`(input: String) {
        // given
        val properties = input.split(" ").map { it.toInt() }

        assertThrows<IllegalArgumentException> { // then
            Position(properties[0], properties[1]) // when
        }
    }
}
