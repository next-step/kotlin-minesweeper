package mine

import mine.cell.Position
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PositionTest {

    @Test
    fun `셀의 위치 정보는 음수를 가질 수 없다`() {
        val x = -1
        val y = 2

        assertThrows<IllegalArgumentException> { Position(x, y) }
    }
}
