package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class NumberTest : StringSpec({
    "값끼리 더하기 연산을 할 수 있다." {
        val number1 = Number(1)
        val number2 = Number(2)

        val result = number1 + number2
        result shouldBe Number(3)
    }
})
