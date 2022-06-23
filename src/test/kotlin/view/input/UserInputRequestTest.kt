package view.input

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import view.input.converter.InputConverter

class UserInputRequestTest {
    @Test
    fun `UserInputRequest는 사용자에게 입력을 요청하기 위한 정보를 보관한다`() {
        val message = "입력해 주세요."
        val inputConverter = object : InputConverter<String> {
            override fun convert(input: String): String {
                return input
            }
        }
        val userInputRequest = UserInputRequest(
            message = message,
            inputConverter = inputConverter
        )

        assertAll(
            { assertThat(userInputRequest.message).isEqualTo(message) },
            { assertThat(userInputRequest.inputConverter).isEqualTo(inputConverter) }
        )
    }
}
