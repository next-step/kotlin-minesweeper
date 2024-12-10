package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineTest {
    @Test
    fun `지뢰는 x좌표 y좌표를 가진다`() {
        val mine = Mine(2, 1)
        assertThat(mine.getXPosition()).isEqualTo(2)
        assertThat(mine.getYPosition()).isEqualTo(1)
    }
}
