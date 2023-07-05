package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `GROUND Cell 을 생성할 수 있다`() {
        with(Cell.ground()) {
            assertThat(type).isEqualTo(CellType.GROUND)
        }
    }

    @Test
    fun `MINE Cell 을 생성할 수 있다`() {
        with(Cell.mine()) {
            assertThat(type).isEqualTo(CellType.MINE)
        }
    }
}
