package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `GROUND Cell 을 생성할 수 있다`() {
        with(Cell.ground(0, 0)) {
            assertThat(type).isEqualTo(CellType.GROUND)
        }
    }

    @Test
    fun `MINE Cell 을 생성할 수 있다`() {
        with(Cell.mine(0, 0)) {
            assertThat(type).isEqualTo(CellType.MINE)
        }
    }

    @Test
    fun `Cell 은 x 좌표를 갖을 수 있다`() {
        // given
        val x = 10
        val y = 11

        // when
        val cell = Cell.ground(x, y)

        // then
        assertThat(cell.position.x).isEqualTo(10)
    }

    @Test
    fun `Cell 은 y 좌표를 갖을 수 있다`() {
        // given
        val x = 10
        val y = 11

        // when
        val cell = Cell.ground(x, y)

        // then
        assertThat(cell.position.y).isEqualTo(11)
    }
}
