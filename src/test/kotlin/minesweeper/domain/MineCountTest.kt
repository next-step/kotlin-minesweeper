package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MineCountTest : StringSpec({
    "지뢰 개수는 음수면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { MineCount(-1) }
    }
})
