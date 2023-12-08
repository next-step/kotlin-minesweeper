package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCountTest : StringSpec({
    "지뢰의 수는 1개 이상 이여야 한다." {
        shouldThrow<IllegalArgumentException> {
            MineCount.from(0)
        }

        MineCount.from(1).toInt() shouldBe 1
        MineCount.from(10).toInt() shouldBe 10
        MineCount.from(100).toInt() shouldBe 100
        MineCount.from(1000).toInt() shouldBe 1000
    }
})
