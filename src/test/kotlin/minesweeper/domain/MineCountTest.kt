package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MineCountTest : StringSpec({
    "지뢰 개수는 1 이상 이어야 한다." {
        forAll(
            row(0),
            row(-1),
        ) { count ->
            val exception = shouldThrow<IllegalArgumentException> { MineCount(count) }
            exception.message shouldBe "지뢰 개수는 1 이상 이어야 합니다."
        }
    }
})
