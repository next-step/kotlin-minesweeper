package minesweeper.domain.point

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class LandTest : StringSpec({
    "땅 좌표는 음수가 될 수 없다." {
        listOf(
            -1 to 1,
            1 to -1,
            -1 to -1
        ).forEach { (row, col) ->
            shouldThrow<IllegalArgumentException> { Land(row, col) }
        }
    }
})
