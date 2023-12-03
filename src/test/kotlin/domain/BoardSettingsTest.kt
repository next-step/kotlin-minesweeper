package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardSettingsTest {

    @Test
    fun `height 가 0 이하이면 IllegalArgumentException 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardSettings(row = 0, col = 3, mineCount = 5)
        }
    }

    @Test
    fun `width 가 0 이하이면 IllegalArgumentException 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardSettings(row = 3, col = 0, mineCount = 5)
        }
    }

    @Test
    fun `지뢰의 갯수가 board 의 크기보다 크면 IllegalArgumentException 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardSettings(row = 3, col = 3, mineCount = 10)
        }
    }
}
