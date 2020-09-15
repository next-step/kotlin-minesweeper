package minesweeper.domain.squarestate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BoundaryTest {
    private lateinit var boundary: Boundary

    @BeforeEach
    fun setUp() {
        boundary = Boundary()
    }

    @Test
    fun `지뢰 아님`() {
        assertThat(boundary.isMine).isFalse()
    }

    @Test
    fun `심볼 확인`() {
        // when
        val symbol: String = boundary.toString()

        // then
        assertThat(symbol).isEqualTo(SquareSymbol.BOUNDARY.symbol)
    }
}
