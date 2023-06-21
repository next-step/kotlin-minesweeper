package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PositiveTest : StringSpec({

    "0보다 큰 수를 입력하면 정상적으로 생성된다." {
        forAll(
            row(1),
            row(10),
            row(100),
            row(9),
            row(99),
            row(33),
        ) { number ->
            val positiveNumber = PositiveNumber(value = number)

            positiveNumber.value shouldBe number
        }
    }

    "1보다 작은 수를 입력하면 IllegalArgumentException 에러가 발생한다." {
        forAll(
            row(0),
            row(-10),
        ) { number ->
            val exception = shouldThrow<IllegalArgumentException> {
                PositiveNumber(value = number)
            }

            exception shouldHaveMessage "숫자는 0보다 큰 값을 입력해야 합니다. 입력값 : $number"
        }
    }
})
