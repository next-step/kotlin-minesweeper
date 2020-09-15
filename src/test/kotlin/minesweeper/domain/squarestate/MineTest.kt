package minesweeper.domain.squarestate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineTest {
    private lateinit var mine: Mine

    @BeforeEach
    fun setUp() {
        mine = Mine()
    }

    @Test
    fun `지뢰임`() {
        assertThat(mine.isMine).isTrue()
    }

    @Test
    fun `심볼 확인`() {
        // when
        val symbol: String = mine.toString()

        // then
        assertThat(symbol).isEqualTo(SquareSymbol.MINE.symbol)
    }
}
