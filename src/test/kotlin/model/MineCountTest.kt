package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class MineCountTest : StringSpec({
    "0 이상의 값에 대해서 MineCount 클래스가 정상적으로 생성된다" {
        table(
            headers("mineCount"),
            row(0),
            row(Int.MAX_VALUE / 2),
            row(Int.MAX_VALUE),
        ).forAll { mineCount ->
            shouldNotThrowAny {
                val sut = MineCount(mineCount)
                sut.value shouldBe mineCount
            }
        }
    }

    "0 이하의 값이 주어지면 예외가 발생한다" {
        table(
            headers("invalidMineCount"),
            row(-1),
            row(Int.MIN_VALUE / 2),
            row(Int.MIN_VALUE),
        ).forAll { invalidMineCount ->
            shouldThrow<IllegalArgumentException> {
                val sut = MineCount(invalidMineCount)
                sut.value shouldBe invalidMineCount
            }
        }
    }
})
