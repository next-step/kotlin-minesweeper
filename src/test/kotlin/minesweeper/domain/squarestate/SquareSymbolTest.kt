package minesweeper.domain.squarestate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SquareSymbolTest {

    @Test
    fun `심볼 확인`() {
        // given
        val expected: List<String> = listOf("", "✴")

        // when
        val actual: List<String> = SquareSymbol.values().map(SquareSymbol::symbol)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
