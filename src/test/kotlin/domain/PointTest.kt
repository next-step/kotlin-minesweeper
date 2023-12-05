package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PointTest {

    @Test
    fun `1,2 를 입력하면 (1,2) 를 가진 Point 가 반환 되어야 한다`() {
        val result = Point.parsePoint("1, 2")
        val excepted = Point(1, 2)

        assertEquals(excepted.row, 1)
        assertEquals(excepted.col, 2)
    }

    @Test
    fun `1, abc 를 입력하면 잘못된 입력이므로 IllegalArgumentException 가 발생해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Point.parsePoint("1,abc")
        }
    }
}
