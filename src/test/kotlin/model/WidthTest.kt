package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

internal class WidthTest : StringSpec({
    "0 이상의 값에 대해서 Width 클래스가 정상적으로 생성된다" {
        table(
            headers("width"),
            row(0),
            row(Int.MAX_VALUE / 2),
            row(Int.MAX_VALUE),
        ).forAll { width ->
            shouldNotThrowAny {
                val sut = Width(width)
                sut.value shouldBe width
            }
        }
    }

    "0 이하의 값이 주어지면 예외가 발생한다" {
        table(
            headers("invalidWidth"),
            row(-1),
            row(Int.MIN_VALUE / 2),
            row(Int.MIN_VALUE),
        ).forAll { invalidWidth ->
            shouldThrow<IllegalArgumentException> {
                val sut = Width(invalidWidth)
                sut.value shouldBe invalidWidth
            }
        }
    }
})
