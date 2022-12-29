package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

internal class ColumnTest : StringSpec({
    "너비가 0 이하라면 에러가 발생한다." {
        val columns = listOf(-1, 0)

        columns.forAll {
            val exception = shouldThrow<IllegalArgumentException> {
                Column(it)
            }
            exception.message should startWith("너비는 1 이상이여야 합니다.")
        }
    }
})
