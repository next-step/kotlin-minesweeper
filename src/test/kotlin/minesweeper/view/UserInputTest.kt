package minesweeper.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserInputTest {
    @Test
    fun `숫자를 받는다`() {
        val userInput = UserInput.Int("질문", "10\n")
        assertThat(userInput.answer()).isEqualTo(10)
    }
}
