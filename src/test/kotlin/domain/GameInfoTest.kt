package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class GameInfoTest {
    @Test
    internal fun `지뢰 개수가 보드 넓이보다 크면 GameInfo를 생성할 때 IllegalArgumentException을 반환한다`() {
        assertThrows<IllegalArgumentException> {
            GameInfo.of(5, 5, 26)
        }
    }

    @Test
    internal fun `정상적으로 GameInfo를 생성한다`() {
        assertDoesNotThrow {
            GameInfo.of(3, 3, 5)
        }
    }
}
