package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class HeightTest : StringSpec({
    "0 이상의 값에 대해서 Height 클래스가 정상적으로 생성된다" {
        table(
            headers("height"),
            row(0),
            row(Int.MAX_VALUE / 2),
            row(Int.MAX_VALUE),
        ).forAll { height ->
            shouldNotThrowAny {
                val sut = Width(height)
                sut.value shouldBe height
            }
        }
    }

    "0 이하의 값이 주어지면 예외가 발생한다" {
        table(
            headers("invalidHeight"),
            row(-1),
            row(Int.MIN_VALUE / 2),
            row(Int.MIN_VALUE),
        ).forAll { height ->
            shouldThrow<IllegalArgumentException> {
                val sut = Height(height)
                sut.value shouldBe height
            }
        }
    }
})
