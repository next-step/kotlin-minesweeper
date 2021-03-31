package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    @Test
    fun `x,y가 0 이상만 조회`() {
        val rounds = Position(NaturalNumber.ZERO, NaturalNumber.ZERO).around

        val expect = listOf(
            Position(NaturalNumber(1), NaturalNumber(0)),
            Position(NaturalNumber(0), NaturalNumber(1)),
            Position(NaturalNumber(1), NaturalNumber(1))
        )

        assertThat(rounds).containsAll(expect)
    }

    @Test
    fun `주변 8개 조회`() {
        val rounds = Position(NaturalNumber(1), NaturalNumber(1)).around

        val expect = listOf(
            Position(NaturalNumber(0), NaturalNumber(0)),
            Position(NaturalNumber(0), NaturalNumber(1)),
            Position(NaturalNumber(0), NaturalNumber(2)),
            Position(NaturalNumber(1), NaturalNumber(0)),
            Position(NaturalNumber(1), NaturalNumber(2)),
            Position(NaturalNumber(2), NaturalNumber(0)),
            Position(NaturalNumber(2), NaturalNumber(1)),
            Position(NaturalNumber(2), NaturalNumber(2))
        )

        assertThat(rounds).containsAll(expect)
    }
}
