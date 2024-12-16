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
        val gameBoard =
            GameBoard.from(
                listOf(
                    listOf(
                        Landmine(Location(row = 1, column = 1)),
                        BasicCell(Location(row = 1, column = 2)),
                        BasicCell(Location(row = 1, column = 3)),
                    ),
                    listOf(
                        BasicCell(Location(row = 2, column = 1)),
                        Landmine(Location(row = 2, column = 2)),
                        BasicCell(Location(row = 2, column = 3)),
                    ),
                    listOf(
                        BasicCell(Location(row = 3, column = 1)),
                        BasicCell(Location(row = 3, column = 2)),
                        BasicCell(Location(row = 3, column = 3)),
                    ),
                ),
            )
        val sut = DefaultLandmineTracker()

        `when`("gameBoard 와 중간 landmineLocation(1,1) 을 받아") {
            val landmineLocation = Location(row = 1, column = 1)
            val result =
                sut.withUpdatedAdjacentMineCounts(gameBoard, landmineLocation)

            then("BasicCell의 인접 지뢰 개수를 표시한 GameBoard를 반환할 수 있다") {
                val allCells = result.rows.flatMap { it.cells() }
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

                allCells shouldBe expectedCells
            }
        }

        `when`("gameBoard 와 landmineLocation(2,2) 을 받아") {
            val landmineLocation = Location(row = 2, column = 2)
            val result =
                sut.withUpdatedAdjacentMineCounts(gameBoard, landmineLocation)

            then("BasicCell의 인접 지뢰 개수를 표시한 GameBoard를 반환할 수 있다") {
                val allCells = result.rows.flatMap { it.cells() }
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

                allCells shouldBe expectedCells
            }
        }
    }
})
