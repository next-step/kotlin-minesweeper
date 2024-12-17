package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultLandmineTrackerTest : BehaviorSpec({
    given("DefaultLandmineTracker 는 ") {
        /**
         * * 0 0
         * 0 * 0
         * 0 0 0
         */
        val allCells =
            listOf(
                Landmine(Location(row = 1, column = 1)),
                BasicCell(Location(row = 1, column = 2)),
                BasicCell(Location(row = 1, column = 3)),
                BasicCell(Location(row = 2, column = 1)),
                Landmine(Location(row = 2, column = 2)),
                BasicCell(Location(row = 2, column = 3)),
                BasicCell(Location(row = 3, column = 1)),
                BasicCell(Location(row = 3, column = 2)),
                BasicCell(Location(row = 3, column = 3)),
            )
        val sut = DefaultLandmineTracker()

        `when`("allCells 와 landmineLocation(1,1) 을 받아") {
            val landmineLocation = Location(row = 1, column = 1)
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("landmineLocation 주변 셀의 인접 지뢰 개수를 표시한 List<Cell> 을 반환한다") {
                val expectedCells =
                    listOf(
                        Landmine(Location(row = 1, column = 1)),
                        BasicCell(Location(row = 1, column = 2), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 1, column = 3), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(1)),
                        Landmine(Location(row = 2, column = 2)),
                        BasicCell(Location(row = 2, column = 3), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 1), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 2), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 3), NumberOfAdjacentMines.ZERO),
                    )

                result shouldBe expectedCells
            }
        }

        `when`("gameBoard 와 landmineLocation(2,2) 을 받아") {
            val landmineLocation = Location(row = 2, column = 2)
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("landmineLocation 주변 셀의 인접 지뢰 개수를 표시한 List<Cell> 을 반환한다") {
                val expectedCells =
                    listOf(
                        Landmine(Location(row = 1, column = 1)),
                        BasicCell(Location(row = 1, column = 2), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 1, column = 3), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(1)),
                        Landmine(Location(row = 2, column = 2)),
                        BasicCell(Location(row = 2, column = 3), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 3, column = 1), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 3, column = 2), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 3, column = 3), NumberOfAdjacentMines(1)),
                    )

                result shouldBe expectedCells
            }
        }
    }
})
