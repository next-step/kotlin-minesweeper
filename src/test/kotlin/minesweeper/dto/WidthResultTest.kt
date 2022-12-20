package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WidthResultTest {

    @Test
    @DisplayName("10 들어올 경우 너비는 10")
    fun `If 10 when entered, the width is 10`() {
        val value = "10"

        val widthResult = WidthResult(value)

        widthResult.width shouldBe 10
    }

    @Test
    @DisplayName("너비 값이 숫자가 아닌 문자가 들어올 경우 IllegalArgumentException")
    fun `IllegalArgumentException when non-numeric characters are entered`() {
        val value = "aaa"

        shouldThrow<IllegalArgumentException> { WidthResult(value) }
    }
    @Test
    @DisplayName("너비 값이 숫자가 아닌 공백 들어올 경우 IllegalArgumentException")
    fun `IllegalwArgumentException when blank are entered`() {
        val value = ""

        shouldThrow<IllegalArgumentException> { WidthResult(value) }
    }
}
