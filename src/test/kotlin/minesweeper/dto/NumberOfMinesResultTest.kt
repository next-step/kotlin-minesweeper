package minesweeper.dto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class NumberOfMinesResultTest {

    @Test
    @DisplayName("10 들어올 경우 지뢰 수는 10")
    fun `If 10 when entered, the mines is 10`() {
        val value = "10"

        val numberOfMinesResult = NumberOfMinesResult(value)

        numberOfMinesResult.number shouldBe 10
    }

    @Test
    @DisplayName("높이 값이 숫자가 아닌 문자가 들어올 경우 IllegalArgumentException")
    fun `IllegalArgumentException when non-numeric characters are entered`() {
        val value = "aaa"

        shouldThrow<IllegalArgumentException> { NumberOfMinesResult(value) }
    }

    @Test
    @DisplayName("높이 값이 숫자가 아닌 공백 들어올 경우 IllegalArgumentException")
    fun `IllegalwArgumentException when blank are entered`() {
        val value = ""

        shouldThrow<IllegalArgumentException> { NumberOfMinesResult(value) }
    }
}
