package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineLessSpotTest {
    private lateinit var mineLessSpot: MineLessSpot

    @BeforeEach
    fun setUp() {
        mineLessSpot = MineLessSpot(3, 3)
    }

    @Test
    fun `원하는 자리에 지뢰없는 땅 배치`() {
        assertThat(mineLessSpot).isEqualTo(MineLessSpot(3, 3))
    }

    @Test
    fun `(근처) 지뢰자리를 받으면 지뢰개수 1개를 더한다`() {
        // given
        mineLessSpot.addCountIfIsMineSpot(MineSpot(3, 2))
        mineLessSpot.addCountIfIsMineSpot(MineLessSpot(3, 2))

        // when
        val count = mineLessSpot.getMineCount()

        // then
        assertThat(count).isEqualTo(1)
    }
}
