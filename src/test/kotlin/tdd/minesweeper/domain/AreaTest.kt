package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class AreaTest : BehaviorSpec({
    given("보드의 영역은") {
        `when`("높이가") {
            val height = 0
            val width = 1

            then("양수여야 한다") {
                shouldThrow<IllegalArgumentException> { Area(height = height, width = width) }
            }
        }
        `when`("너비가") {
            val height = 1
            val width = 0

            then("양수여야 한다") {
                shouldThrow<IllegalArgumentException> { Area(height = height, width = width) }
            }
        }
    }
})
