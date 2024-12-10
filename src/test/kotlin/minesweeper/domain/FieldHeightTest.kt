package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FieldHeightTest : StringSpec({
    "높이는 1 이상 이어야 한다." {
        forAll(
            row(0),
            row(-1),
        ) { height ->
            val exception = shouldThrow<IllegalArgumentException> { FieldHeight(height) }
            exception.message shouldBe "높이는 1 이상 이어야 합니다."
        }
    }
})
