package minesweeper.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.domain.data.PositiveNumber

class PositiveIntTest : StringSpec({
    " PositiveInt 는 0 보다 커야 합니다." {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber(number = 0)
        }
    }
})
