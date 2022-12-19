package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

internal class RowTest: StringSpec({
    "너비가 0 이라면 IllegalArgumentException 이 발생한다." {
        val exception = shouldThrow<IllegalArgumentException> {
            Row(0)
        }
        exception.message should startWith("높이는 0보다 큰 정수여야 합니다.")
    }

    "너비가 음수라면 IllegalArgumentException 이 발생한다." {
        val exception = shouldThrow<IllegalArgumentException> {
            Row(-1)
        }
        exception.message should startWith("높이는 0보다 큰 정수여야 합니다.")
    }
})