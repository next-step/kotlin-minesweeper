package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class CountOfLandminesTest : BehaviorSpec({
    given("CountOfLandmines 는") {
        `when`("음수로 생성하려고 하면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    CountOfLandmines(-1)
                }
            }
        }
    }
})
