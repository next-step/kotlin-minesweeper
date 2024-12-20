package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class AreaTest : BehaviorSpec({
    given("지역을 생성하기 위해서는") {

        `when`("높이가") {
            val height = 0
            val width = 1

            then("1 이상이어야 한다") {
                shouldThrow<IllegalArgumentException> {
                    Area(height = height, width = width)
                }
            }
        }

        `when`("너비가") {
            val height = 1
            val width = 0

            then("1 이상이어야 한다") {
                shouldThrow<IllegalArgumentException> {
                    Area(height = height, width = width)
                }
            }
        }
    }
})
