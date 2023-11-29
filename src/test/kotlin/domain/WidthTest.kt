package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Width

class WidthTest : StringSpec({
    "입력 받은 숫자로 너비 생성" {
        val number = 5

        val width = Width(number)

        width.value shouldBe number
    }

    "높이는 1이상" {
        val number = 0

        shouldThrowExactly<IllegalArgumentException> {
            Width(number)
        }
    }
})
