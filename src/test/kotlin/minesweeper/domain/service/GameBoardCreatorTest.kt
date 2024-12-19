package minesweeper.domain.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.NumberOfAdjacentMines
import minesweeper.domain.oneByOneLocation
import minesweeper.domain.oneByThreeLocation
import minesweeper.domain.oneByTwoLocation
import minesweeper.domain.strategy.DefaultLandmineLocationSelector
import minesweeper.domain.strategy.DefaultLandmineTracker
import minesweeper.domain.strategy.FixedShuffleAlgorithm
import minesweeper.domain.strategy.Vulture
import minesweeper.domain.threeByOneLocation
import minesweeper.domain.threeByThreeLocation
import minesweeper.domain.threeByTwoLocation
import minesweeper.domain.twoByOneLocation
import minesweeper.domain.twoByThreeLocation
import minesweeper.domain.twoByTwoLocation

class GameBoardCreatorTest : BehaviorSpec({
    val fixedLandmineLocationSelector = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())
    val sut =
        GameBoardCreator(
            landmineLocationSelector = fixedLandmineLocationSelector,
            landminePlanter = Vulture(),
            landmineTracker = DefaultLandmineTracker(),
        )

    given("너비 3, 높이 3, 지뢰 개수 3을 받아") {
        val width = 3
        val height = 3
        val countOfLandmines = CountOfLandmines(3)

        `when`("게임판을 생성하면") {
            val result =
                sut.createBoard(width = width, height = height, countOfLandmines = countOfLandmines)

            then("3개의 지뢰가 숨겨지고 인접 지뢰 개수가 기록된 닫힌 셀 9개를 가진 게임판이 만들어져야 한다") {
                result.area shouldBe Area(height = height, width = width)
                result.cells shouldBe
                    listOf(
                        ClosedCell(oneByOneLocation, true, NumberOfAdjacentMines(1)),
                        ClosedCell(oneByTwoLocation, true, NumberOfAdjacentMines(2)),
                        ClosedCell(oneByThreeLocation, true, NumberOfAdjacentMines(1)),
                        ClosedCell(twoByOneLocation, false, NumberOfAdjacentMines(2)),
                        ClosedCell(twoByTwoLocation, false, NumberOfAdjacentMines(3)),
                        ClosedCell(twoByThreeLocation, false, NumberOfAdjacentMines(2)),
                        ClosedCell(threeByOneLocation, false, NumberOfAdjacentMines.ZERO),
                        ClosedCell(threeByTwoLocation, false, NumberOfAdjacentMines.ZERO),
                        ClosedCell(threeByThreeLocation, false, NumberOfAdjacentMines.ZERO),
                    )
            }
        }
    }

    given("너비 3, 높이 3, 지뢰 개수 10개를 받아") {
        val width = 3
        val height = 3
        val countOfLandmines = CountOfLandmines(10)

        `when`("게임판을 생성하려고 하면") {
            then("게임판의 모든 셀 갯수보다 지뢰 개수가 많으므로 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    sut.createBoard(width = width, height = height, countOfLandmines = countOfLandmines)
                }
            }
        }
    }
})
