package domain.board

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.Height

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

    "높이에 대한 열의 범위는 0부터 (높이 - 1)" {
        val value = 10

        Height(value).rowRange shouldBe 0..9
    }
})
