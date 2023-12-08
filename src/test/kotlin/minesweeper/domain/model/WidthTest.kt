package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WidthTest : StringSpec({

    "넓이는 최소 2이상, 최대 1000이하 여야 한다" {
        shouldThrow<IllegalArgumentException> {
            Width.from(0)
        }
        shouldThrow<IllegalArgumentException> {
            Width.from(1)
        }
        shouldThrow<IllegalArgumentException> {
            Width.from(1001)
        }

        Width.from(2).toInt() shouldBe 2
        Width.from(500).toInt() shouldBe 500
        Width.from(1000).toInt() shouldBe 1000
    }
})
