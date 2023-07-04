package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InputParserTest {

    @Test
    fun `쉼표를 기준으로 숫자를 구분한다`() {
        val input = "1, 2"
        InputParser.parse(input).size shouldBe 2
    }
}
