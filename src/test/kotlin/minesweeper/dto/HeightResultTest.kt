package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class HeightResultTest {

    @Test
    @DisplayName("10 들어올 경우 높이는 10")
    fun `If 10 when entered, the height is 10`() {
        val value = "10"

        val heightResult = HeightResult(value)

        heightResult.height shouldBe 10
    }

    @Test
    @DisplayName("높이 값이 숫자가 아닌 문자가 들어올 경우 IllegalArgumentException")
    fun `IllegalArgumentException when non-numeric characters are entered`() {
        val value = "aaa"

        shouldThrow<IllegalArgumentException> { HeightResult(value) }
    }

    @Test
    @DisplayName("높이 값이 숫자가 아닌 공백 들어올 경우 IllegalArgumentException")
    fun `IllegalwArgumentException when blank are entered`() {
        val value = ""

        shouldThrow<IllegalArgumentException> { HeightResult(value) }
    }
}
