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

            then("기본적으로는 인접 지뢰 개수가 0개이다") {
                sut.numberOfAdjacentLandmines shouldBe NumberOfAdjacentMines.ZERO
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

    given("새로운 인접 지뢰 개수를 가지고") {
        val location = oneByOneLocation
        val sut = ClosedCell(location)
        val newNumberOfAdjacentMines = NumberOfAdjacentMines(8)

        `when`("withNumberOfAdjacentLandmines() 를 호출하면") {
            val result = sut.withNumberOfAdjacentLandmines(newNumberOfAdjacentMines)

            then("해당 인접 지뢰 개수로 업데이트된 ClosedCell 을 반환한다") {
                sut.location shouldBe result.location
                sut.symbol shouldBe result.symbol
                sut.hasLandmine shouldBe result.hasLandmine

                sut.numberOfAdjacentLandmines shouldBe NumberOfAdjacentMines.ZERO
                result.numberOfAdjacentLandmines shouldBe newNumberOfAdjacentMines
            }
        }
    }

    given("지뢰를 심기 위해") {
        val location = oneByOneLocation
        val sut = ClosedCell(location)

        `when`("plantMine()을 호출하면") {
            val result = sut.plantMine()

            then("hasLandmine 이 true 로 업데이트 된 ClosedCell 을 반환한다") {
                sut.location shouldBe result.location
                sut.symbol shouldBe result.symbol
                sut.numberOfAdjacentLandmines shouldBe result.numberOfAdjacentLandmines

                sut.hasLandmine shouldBe false
                result.hasLandmine shouldBe true
            }
        }
    }
})
