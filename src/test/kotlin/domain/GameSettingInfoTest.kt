package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Created by Jaesungchi on 2022.06.28..
 */
class GameSettingInfoTest {

    @Test
    fun `높이가 0이랑 같거나 작다면 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            GameSettingInfo(-1, 1, 1)
        }
    }

    @Test
    fun `너비가 0이랑 같거나 작다면 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            GameSettingInfo(1, -1, 1)
        }
    }

    @Test
    fun `지뢰갯수가 0보다 작다면 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            GameSettingInfo(1, 1, -1)
        }
    }
}
