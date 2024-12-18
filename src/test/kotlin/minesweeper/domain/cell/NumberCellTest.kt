package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.oneByOneLocation

class NumberCellTest : BehaviorSpec({
    given("Location 와 NumberOfAdjacentMines.ZERO 를 받아") {
        val location = oneByOneLocation
        val numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO

        `when`("생성하면") {
            val sut = NumberCell(location, numberOfAdjacentLandmines)

            then("해당 Location 을 가지고 있다") {
                sut.location shouldBe location
            }
        }
    }

    given("생성할 때의 NumberOfAdjacentMines 에 따라") {
        val location = oneByOneLocation

        `when`("생성된 NumberCell 의") {
            then("Symbol 이 다르다") {
                listOf(
                    NumberOfAdjacentMines.ZERO,
                    NumberOfAdjacentMines(1),
                    NumberOfAdjacentMines(2),
                    NumberOfAdjacentMines(3),
                    NumberOfAdjacentMines(4),
                    NumberOfAdjacentMines(5),
                    NumberOfAdjacentMines(6),
                    NumberOfAdjacentMines(7),
                    NumberOfAdjacentMines(8),
                ).forEach { numberOfAdjacentMines ->
                    val sut = NumberCell(location, numberOfAdjacentMines)

                    sut.symbol shouldBe Symbol.from(numberOfAdjacentMines)
                }
            }
        }
    }
})
