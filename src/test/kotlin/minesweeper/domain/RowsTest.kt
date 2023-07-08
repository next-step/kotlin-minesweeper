package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RowsTest : StringSpec({
    "너비의 값은 0보다 커야 한다" {
        val exception = shouldThrow<IllegalArgumentException> { Rows(0) }
        exception.message shouldBe "너비는 0보다 커야 합니다."
    }

    "너비의 값이 1보다 클 때 정상 생성 된다." {
        val rows = Rows(1)
        rows.value shouldBe 1
    }
})
