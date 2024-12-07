package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HeightTest : StringSpec({
    "높이가 1이상이면 Height 객체를 생성할 수 있다" {
        // Arrange:
        val value = 1

        // Act:
        val height = Height(value)

        // Assert:
        height shouldBe Height(1)
    }

    "높이가 0이하면 IllegalArgumentException이 발생한다" {
        // Arrange:
        val value = 0

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            Height(value)
        }

        // Assert:
        result.message shouldBe "높이는 1이상이어야 합니다. input = $value"
    }
})
