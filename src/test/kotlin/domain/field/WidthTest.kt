package domain.field

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.field.Width

class WidthTest : StringSpec({
    "입력 받은 숫자로 너비 생성" {
        val number = 5

        val width = Width(number)

        width.value shouldBe number
    }

    "너비는 1이상" {
        val number = 0

        shouldThrowExactly<IllegalArgumentException> {
            Width(number)
        }
    }

    "너비에 대한 행의 범위는 0부터 (너비 - 1)" {
        val value = 10

        Width(value).columnRange shouldBe 0..9
    }
})
