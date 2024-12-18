package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.cell.BasicCell
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines

class DefaultLandmineTrackerTest : BehaviorSpec({
    given("DefaultLandmineTracker 는 ") {
        /**
         * * 0 0
         * 0 * 0
         * 0 0 0
         */
        val allCells =
            Cells(
                LandmineCell(Location(row = 1, column = 1)),
                BasicCell(Location(row = 1, column = 2)),
                BasicCell(Location(row = 1, column = 3)),
                BasicCell(Location(row = 2, column = 1)),
                LandmineCell(Location(row = 2, column = 2)),
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

            then("landmineLocation 주변 셀의 인접 지뢰 개수를 표시한 Cells 을 반환한다") {
                val expectedCells =
                    Cells(
                        listOf(
                            LandmineCell(Location(row = 1, column = 1)),
                            BasicCell(Location(row = 1, column = 2), NumberOfAdjacentMines(1)),
                            BasicCell(Location(row = 1, column = 3), NumberOfAdjacentMines.ZERO),
                            BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(1)),
                            LandmineCell(Location(row = 2, column = 2)),
                            BasicCell(Location(row = 2, column = 3), NumberOfAdjacentMines.ZERO),
                            BasicCell(Location(row = 3, column = 1), NumberOfAdjacentMines.ZERO),
                            BasicCell(Location(row = 3, column = 2), NumberOfAdjacentMines.ZERO),
                            BasicCell(Location(row = 3, column = 3), NumberOfAdjacentMines.ZERO),
                        ),
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
                        LandmineCell(Location(row = 1, column = 1)),
                        BasicCell(Location(row = 1, column = 2), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 1, column = 3), NumberOfAdjacentMines(1)),
                        BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(1)),
                        LandmineCell(Location(row = 2, column = 2)),
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
