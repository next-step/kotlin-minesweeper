package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.oneByOneLocation

class LandmineCellTest : BehaviorSpec({
    given("LandmineCell 는") {
        val location = oneByOneLocation
        val sut = LandmineCell(location)

        `when`("생성하면") {
            then("자신의 위치를 알 수 있다") {
                val result = sut.location
                result shouldBe oneByOneLocation
            }

            then("기본 심볼은 LANDMINE 이다") {
                sut.symbol shouldBe Symbol.LANDMINE
            }
        }
    }
})
