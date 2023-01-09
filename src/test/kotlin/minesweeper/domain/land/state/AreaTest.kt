package minesweeper.domain.land.state

import minesweeper.Area
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AreaTest {
    @Test
    fun `Area - 너비, 높이, 크기 가져오기 테스트`() {
        // given
        val area = Area(10, 10)

        // when
        val size = area.size()

        // then
        assertThat(area.width).isEqualTo(10)
        assertThat(area.height).isEqualTo(10)
        assertThat(size).isEqualTo(100)
    }
}
