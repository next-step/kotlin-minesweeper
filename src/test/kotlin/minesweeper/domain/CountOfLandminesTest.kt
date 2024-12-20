package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class CountOfLandminesTest : BehaviorSpec({
    given("지뢰 개수 는") {
        `when`("0 미만을") {
            then("허용하지 않는다") {
                shouldThrow<IllegalArgumentException> {
                    CountOfLandmines(-1)
                }
            }
        }
    }
})
