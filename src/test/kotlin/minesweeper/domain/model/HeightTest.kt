package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HeightTest : StringSpec({
    "높이는 최소 2이상, 최대 1000이하 여야 한다" {
        shouldThrow<IllegalArgumentException> {
            Height.from(0)
        }
        shouldThrow<IllegalArgumentException> {
            Height.from(1)
        }
        shouldThrow<IllegalArgumentException> {
            Height.from(1001)
        }

        Height.from(2).toInt() shouldBe 2
        Height.from(500).toInt() shouldBe 500
        Height.from(1000).toInt() shouldBe 1000
    }
})
