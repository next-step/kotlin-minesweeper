package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSpotTest {

    @Test
    fun `원하는 자리에 지뢰 배치`() {
        // when
        val mineSpot = MineSpot(1, 1)

        // given
        assertThat(mineSpot).isEqualTo(MineSpot(1, 1))
    }
}
