package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class NaturalNumberTest {

    @Test
    fun `NaturalNumber은 음수일 수 없다`() {
        assertThrows<IllegalArgumentException>("number must be positive") {
            NaturalNumber(-1)
        }
    }

    @Test
    fun `string이 숫자가 아닐 경우 예외 발생한다`() {
        assertThrows<IllegalArgumentException>("") {
            NaturalNumber("invalid string")
        }
    }
}
