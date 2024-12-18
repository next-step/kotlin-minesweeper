package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.oneByOneLocation

class ClosedCellTest : BehaviorSpec({
    given("Location 을 받아") {
        val location = oneByOneLocation

        `when`("생성하면") {
            val sut = ClosedCell(location)

            then("해당 Location 을 가지고 있다") {
                sut.location shouldBe location
            }

            then("Symbol.CLOSED 를 가진다") {
                sut.symbol shouldBe Symbol.CLOSED
            }

            then("기본적으로는 지뢰를 갖고 있지 않다") {
                sut.hasLandmine shouldBe false
            }
        }
    }

    given("Landmine 을 가지고 있는 ClosedCell 은") {
        val sut = ClosedCell(oneByOneLocation, hasLandmine = true)

        `when`("open() 하면") {
            val result = sut.open()

            then("LandmineCell 을 반환한다") {
                result.shouldBeInstanceOf<LandmineCell>()
            }
        }
    }

    given("Landmine 을 가지고 있지 않은 ClosedCell 은") {
        val sut = ClosedCell(oneByOneLocation)

        `when`("open() 하면") {
            val result = sut.open()

            then("NumberCell 을 반환한다") {
                result.shouldBeInstanceOf<NumberCell>()
            }
        }
    }
})
