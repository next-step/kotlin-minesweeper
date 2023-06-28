package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec

class HeightTest : AnnotationSpec() {

    @Test
    fun `높이는 숫자이어야함`() {
        shouldThrow<IllegalArgumentException> {
            Height("f")
        }

        shouldNotThrow<IllegalArgumentException> {
            Height("1")
        }
    }

    @Test
    fun `높이는 0보다 커야함`() {
        shouldThrow<IllegalArgumentException> {
            Height("-1")
        }

        shouldThrow<IllegalArgumentException> {
            Height("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            Height("1")
        }
    }
}
