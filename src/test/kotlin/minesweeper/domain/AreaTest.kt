package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class AreaTest : BehaviorSpec({
    given("Area 를 생성할 때") {

        `when`("높이가 1 미만이면") {
            val height = 0
            val width = 1

            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    Area(height = height, width = width)
                }
            }
        }

        `when`("너비가 1 미만이면") {
            val height = 1
            val width = 0

            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    Area(height = height, width = width)
                }
            }
        }
    }
})
