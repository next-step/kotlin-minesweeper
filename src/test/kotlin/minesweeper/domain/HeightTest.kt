package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class HeightTest : StringSpec({
    "높이는 음수면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Height(-1) }
    }
})
