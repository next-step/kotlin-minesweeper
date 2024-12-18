package minesweeper.domain.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.BasicCell
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines
import minesweeper.domain.strategy.DefaultLandmineLocationSelector
import minesweeper.domain.strategy.DefaultLandmineTracker
import minesweeper.domain.strategy.FixedShuffleAlgorithm
import minesweeper.domain.strategy.Vulture

class GameBoardCreatorTest : BehaviorSpec({
    val fixedLandmineLocationSelector = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())
    val sut =
        GameBoardCreator(
            landmineLocationSelector = fixedLandmineLocationSelector,
            landminePlanter = Vulture(),
            landmineTracker = DefaultLandmineTracker(),
        )

    given("width, height, countOfLandmines 를 받아") {
        val width = 3
        val height = 3
        val countOfLandmines = CountOfLandmines(3)

        `when`("design2() 를 호출하면") {
            val result =
                sut.design(width = width, height = height, countOfLandmines = countOfLandmines)

            then("지뢰 셀과 지뢰 인접 개수가 포함된 셀이 포함된 gameBoard를 반환한다") {
                result.area shouldBe Area(height = height, width = width)
                result.cells shouldBe
                    listOf(
                        LandmineCell(Location(row = 1, column = 1)),
                        LandmineCell(Location(row = 1, column = 2)),
                        LandmineCell(Location(row = 1, column = 3)),
                        BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(2)),
                        BasicCell(Location(row = 2, column = 2), NumberOfAdjacentMines(3)),
                        BasicCell(Location(row = 2, column = 3), NumberOfAdjacentMines(2)),
                        BasicCell(Location(row = 3, column = 1), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 2), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 3), NumberOfAdjacentMines.ZERO),
                    )
            }
        }
    }

    given("width, height, 그리고 width * height 보다 큰 countOfLandmines 를 받아") {
        val width = 3
        val height = 3
        val countOfLandmines = CountOfLandmines(10)

        `when`("design2() 를 호출하면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    sut.design(width = width, height = height, countOfLandmines = countOfLandmines)
                }
            }
        }
    }
})
