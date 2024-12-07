package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WidthTest : StringSpec({
    "너비가 1이상이면 Width 객체를 생성할 수 있다" {
        // Arrange:
        val value = 1

        // Act:
        val width = Width(value)

        // Assert:
        width shouldBe Width(1)
    }

    "너비가 0이하면 IllegalArgumentException이 발생한다" {
        // Arrange:
        val value = 0

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            Width(value)
        }

        // Assert:
        result.message shouldBe "너비는 1이상이어야 합니다. input = $value"
    }
})
