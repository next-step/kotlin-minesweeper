package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionTest {

    @CsvSource(
        value = [
            "0,0,0,0,0",
            "0,0,0,1,-1",
            "0,1,0,0,1"
        ]
    )
    @ParameterizedTest
    fun `compare test`(x1: Int, y1: Int, x2: Int, y2: Int, result: Int) {
        val first = Position(NaturalNumber(x1), NaturalNumber(y1))
        val second = Position(NaturalNumber(x2), NaturalNumber(y2))
        assertThat(first.compareTo(second)).isEqualTo(result)
    }
}
