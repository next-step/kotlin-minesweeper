package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PointTest : StringSpec({
    "좌표는 음수가 될 수 없다." {
        shouldThrow<IllegalArgumentException> { Point(-1, -1) }
    }
})
