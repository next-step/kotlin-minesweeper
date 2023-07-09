package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class MineNumberTest : FunSpec({

    test("지뢰 갯수는 숫자이어야함") {
        shouldThrow<IllegalArgumentException> {
            MineCount.of("f")
        }
    }

    test("지뢰 갯수는 0보다 커야함") {
        shouldThrow<IllegalArgumentException> {
            MineCount.of("-1")
        }

        shouldThrow<IllegalArgumentException> {
            MineCount.of("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            MineCount.of("1")
        }
    }
})
