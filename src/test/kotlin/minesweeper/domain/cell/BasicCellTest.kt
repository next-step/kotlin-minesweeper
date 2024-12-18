package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BasicCellTest : BehaviorSpec({
    given("BasicCell 은") {
        val row = 1
        val column = 1

        `when`("생성하면") {
            val sut = BasicCell(row = row, column = column)

            then("자신의 위치를 알 수 있다") {
                val result = sut.location
                val expectedLocation = Location(row = row, column = column)

                result shouldBe expectedLocation
            }

            then("기본 심볼은 ZERO 이다") {
                sut.symbol shouldBe Symbol.ZERO
            }
        }

        `when`("withIncrementedNumberOfAdjacentMines 을 호출하여") {
            val location = Location(row = row, column = column)
            val sut = BasicCell(location, NumberOfAdjacentMines.ZERO)
            val result = sut.withIncrementedNumberOfAdjacentMines()

            then("numberOfAdjacentMines 가 1 증가한 BasicCell 을 반환할 수 있다") {
                result.numberOfAdjacentMines shouldBe NumberOfAdjacentMines(1)
            }
        }
    }
})
