package step4.view

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.throwable.shouldHaveMessage

class CoordinateViewTest : FunSpec({

    context("from") {
        forAll(
            row("1"),
            row("1, 2, 3"),
        ) { input ->
            test("입력된 좌표 ${input}가 2개가 아닌 경우 예외가 발생한다.") {
                val exception = shouldThrowExactly<IllegalArgumentException> { CoordinateView.from(input) }
                exception shouldHaveMessage "좌표는 row, column 2개만 입력가능합니다."
            }
        }

        test("row가 숫자가 아닌 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { CoordinateView.from("a, 1") }
            exception shouldHaveMessage "숫자만 입력가능합니다."
        }

        test("column이 숫자가 아닌 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { CoordinateView.from("1, a") }
            exception shouldHaveMessage "숫자만 입력가능합니다."
        }
    }
})
