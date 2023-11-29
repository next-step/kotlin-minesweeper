package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Height

class HeightTest : StringSpec({
    "입력 받은 숫자로 높이 생성" {
        val number = 3

        val height = Height(number)

        height.value shouldBe number
    }

    "높이는 1이상" {
        val number = 0

        shouldThrowExactly<IllegalArgumentException> {
            Height(number)
        }
    }
})
