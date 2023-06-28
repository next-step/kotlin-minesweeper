package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WidthTest : AnnotationSpec(){

    @Test
    fun `너비는 숫자이어야함`() {
        shouldThrow<IllegalArgumentException> {
            Width("f")
        }

        shouldNotThrow<IllegalArgumentException> {
            Width("1")
        }
    }

    @Test
    fun `너비는 0보다 커야함`() {
        shouldThrow<IllegalArgumentException> {
            Width("-1")
        }

        shouldThrow<IllegalArgumentException> {
            Width("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            Width("1")
        }
    }
}
