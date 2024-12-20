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
    given("(1,1), (2,2)에 지뢰가 매설된 3x3의 닫힌 셀들을 받아") {
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

        `when`("(1,1)의 지뢰 위치를 가지고 인접 지뢰 개수를 업데이트 하면") {
            val landmineLocation = oneByOneLocation
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("(1,1) 지뢰 위치에 인접한 (1,2), (2,1), (2,2)에 있는 닫힌 셀의 인접 지뢰 개수가 각 1개로 업데이트 된다") {
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

        `when`("(2,2)의 지뢰 위치를 가지고 인접 지뢰 개수를 업데이트 하면") {
            val landmineLocation = twoByTwoLocation
            val result =
                sut.withUpdatedAdjacentMineCounts(allCells, landmineLocation)

            then("(2,2) 지뢰 위치에 인접한 (1,1), (1,2), (1,3), (2,1), (2,3), (3, 1), (3, 2), (3, 3) 에 있는 닫힌 셀의 인접 지뢰 개수가 각 1개로 업데이트 된다") {
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
