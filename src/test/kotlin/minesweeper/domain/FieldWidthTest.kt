package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FieldWidthTest : StringSpec({
    "너비는 1 이상 이어야 한다." {
        forAll(
            row(0),
            row(-1),
        ) { width ->
            val exception = shouldThrow<IllegalArgumentException> { FieldWidth(width) }
            exception.message shouldBe "너비는 1 이상 이어야 합니다."
        }
    }
})
