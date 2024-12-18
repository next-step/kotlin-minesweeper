package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.NumberOfAdjacentMines
import minesweeper.domain.oneByOneLocation
import minesweeper.domain.oneByThreeLocation
import minesweeper.domain.oneByTwoLocation
import minesweeper.domain.threeByOneLocation
import minesweeper.domain.threeByThreeLocation
import minesweeper.domain.threeByTwoLocation
import minesweeper.domain.twoByOneLocation
import minesweeper.domain.twoByThreeLocation
import minesweeper.domain.twoByTwoLocation

class DefaultLandmineTrackerTest : BehaviorSpec({
    given("DefaultLandmineTracker 는 ") {
        /**
         * * 0 0
         * 0 * 0
         * 0 0 0
         */
        val allCells =
            Cells(
                ClosedCell(oneByOneLocation, hasLandmine = true),
                ClosedCell(oneByTwoLocation),
                ClosedCell(oneByThreeLocation),
                ClosedCell(twoByOneLocation),
                ClosedCell(twoByTwoLocation, hasLandmine = true),
                ClosedCell(twoByThreeLocation),
                ClosedCell(threeByOneLocation),
                ClosedCell(threeByTwoLocation),
                ClosedCell(threeByThreeLocation),
            )
        val sut = DefaultLandmineTracker()

        `when`("allCells 와 landmineLocation(1,1) 을 받아") {
            val landmineLocation = oneByOneLocation
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("landmineLocation 주변 셀의 인접 지뢰 개수를 표시한 Cells 을 반환한다") {
                val expectedCells =
                    Cells(
                        listOf(
                            ClosedCell(oneByOneLocation, hasLandmine = true),
                            ClosedCell(oneByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                            ClosedCell(oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
                            ClosedCell(twoByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                            ClosedCell(twoByTwoLocation, hasLandmine = true, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                            ClosedCell(twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
                            ClosedCell(threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
                            ClosedCell(threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
                            ClosedCell(threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO),
                        ),
                    )

                result shouldBe expectedCells
            }
        }

        `when`("gameBoard 와 landmineLocation(2,2) 을 받아") {
            val landmineLocation = twoByTwoLocation
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("landmineLocation 주변 셀의 인접 지뢰 개수를 표시한 List<Cell> 을 반환한다") {
                val expectedCells =
                    listOf(
                        ClosedCell(oneByOneLocation, hasLandmine = true, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(oneByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(oneByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(twoByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(twoByTwoLocation, hasLandmine = true),
                        ClosedCell(twoByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(threeByOneLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(threeByTwoLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                        ClosedCell(threeByThreeLocation, numberOfAdjacentLandmines = NumberOfAdjacentMines(1)),
                    )

                result shouldBe expectedCells
            }
        }
    }
})
