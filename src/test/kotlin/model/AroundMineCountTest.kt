package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AroundMineCountTest {
    @Test
    fun `0 부터 8 까지 총 9개의 AroundMineCount 가 존재한다`() {
        assertThat(AroundMineCount.values()).containsExactly(
            AroundMineCount.ZERO,
            AroundMineCount.ONE,
            AroundMineCount.TWO,
            AroundMineCount.THREE,
            AroundMineCount.FOUR,
            AroundMineCount.FIVE,
            AroundMineCount.SIX,
            AroundMineCount.SEVEN,
            AroundMineCount.EIGHT
        )
    }
}