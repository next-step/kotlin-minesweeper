package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MinePositionTest : StringSpec({
    "위치는 0보다 커야한다" {
        shouldThrow<IllegalArgumentException> {
            MinePosition.of(-1, -1)
        }
    }
})
