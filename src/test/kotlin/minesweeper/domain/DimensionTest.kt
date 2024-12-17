package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DimensionTest : StringSpec({
    "높이와 너비가 valid 하다면 예외를 던지지 않고 올바른 Dimension 을 계산함" {
        val dimension = Dimension(height = 10, width = 10)
        dimension.totalCells() shouldBe 100
    }

    "높이가 0 이면 예외를 던진다" {
        shouldThrow<IllegalArgumentException> {
            Dimension(height = 0, width = 10)
        }
    }

    "너비가 0 이면 예외를 던진다" {
        shouldThrow<IllegalArgumentException> {
            Dimension(height = 10, width = 0)
        }
    }
})
