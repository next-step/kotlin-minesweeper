package mine

import mine.cell.Position
import mine.cell.Position.Companion.ofNullable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun `셀의 위치 정보는 음수를 가지면 null을 반환한다`() {
        val x = -1
        val y = 2

        val position = Position(x, y).ofNullable()

        assertThat(position).isEqualTo(null)
    }
}
