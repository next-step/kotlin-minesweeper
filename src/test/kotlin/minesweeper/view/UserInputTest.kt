package minesweeper.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.InputMismatchException

class UserInputTest {
    @Test
    fun `숫자를 받는다`() {
        val userInput = UserInput.Int("질문", "10\n")
        assertThat(userInput.answer()).isEqualTo(10)
    }

    @Test
    fun `숫자가 아니면 오류가 발생한다`() {
        assertThrows<InputMismatchException> { UserInput.Int("질문", "a\n").answer() }
    }

    @Test
    fun `숫자 어레이를 받는다`() {
        val userInput = UserInput.IntArray("질문", "1,2\n")
        assertThat(userInput.answer())
            .hasSize(2)
            .contains(1, 2)
    }
}
