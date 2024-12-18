package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LandmineTest : BehaviorSpec({
    given("Landmine 는") {
        val sut = Landmine(row = 1, column = 1)

        `when`("생성하면") {
            then("자신의 위치를 알 수 있다") {
                val result = sut.location
                result shouldBe Location(row = 1, column = 1)
            }

            then("기본 심볼은 LANDMINE 이다") { // TODO 추후 확장
                sut.symbol shouldBe Symbol.LANDMINE
            }
        }
    }
})
