package minesweeper

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class GroundTest {

    @Test
    fun `높이가 자연수가 아닌경우 예외처리된다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Ground(-10)
        }
    }
}