package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

internal class MinesAroundCountTest : StringSpec({
    "지뢰 개수가 0 미만이라면 예외가 발생한다." {
        val values = listOf(-100, -10, -1)
        values.forAll {
            val exception = shouldThrow<IllegalArgumentException> {
                MinesAroundCount(it)
            }
            exception.message should startWith("지뢰 개수는 0 이상이어야 합니다.")
        }
    }

    "지뢰 개수는 0 이상이어야 한다." {
        val values = listOf(0, 1, 5)
        values.forAll {
            shouldNotThrowAny {
                MinesAroundCount(it)
            }
        }
    }
})
