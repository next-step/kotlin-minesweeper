package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec

class MineNumberTest : AnnotationSpec() {

    @Test
    fun `지뢰 갯수는 숫자이어야함`() {
        shouldThrow<IllegalArgumentException> {
            MineCount("f")
        }
    }

    @Test
    fun `지뢰 갯수는 0보다 커야함`() {
        shouldThrow<IllegalArgumentException> {
            MineCount("-1")
        }

        shouldThrow<IllegalArgumentException> {
            MineCount("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            MineCount("1")
        }
    }
}
