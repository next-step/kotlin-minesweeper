package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

internal class RowTest : StringSpec({
    "높이가 0 이하라면 에러가 발생한다." {
        val rows = listOf(-1, 0)

        rows.forAll {
            val exception = shouldThrow<IllegalArgumentException> {
                Row(it)
            }
            exception.message should startWith("높이는 1 이상이어야 합니다.")
        }
    }
})
