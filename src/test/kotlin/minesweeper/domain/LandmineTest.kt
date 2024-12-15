package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LandmineTest : BehaviorSpec({
    given("Landmine 의") {
        val sut = Landmine()

        `when`("기본 상태의 display 값은") {
            val result = sut.display()

            then("'*' 이다") {
                result shouldBe "*"
            }
        }
    }
})
