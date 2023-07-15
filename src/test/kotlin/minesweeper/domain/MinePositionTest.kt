package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MinePositionTest : StringSpec({
    "위치는 0보다 크거나 같아야 한다" {
        shouldThrow<IllegalArgumentException> {
            MinePosition.of(-1, -1)
        }

        shouldNotThrow<IllegalArgumentException> {
            MinePosition.of(0, 0)
        }
    }
})
