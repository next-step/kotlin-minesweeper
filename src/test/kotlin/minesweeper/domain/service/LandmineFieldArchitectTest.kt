package minesweeper.domain.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.BasicCell
import minesweeper.domain.cell.Landmine
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines
import minesweeper.domain.strategy.DefaultLandmineLocationSelector
import minesweeper.domain.strategy.DefaultLandmineTracker
import minesweeper.domain.strategy.FixedShuffleAlgorithm
import minesweeper.domain.strategy.Vulture
import minesweeper.domain.threeByThreeCells

class LandmineFieldArchitectTest : BehaviorSpec({
    given("LandmineFieldArchitect 는 ") {
        val fixedLandmineLocationSelector = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())
        val sut =
            LandmineFieldArchitect(
                landmineLocationSelector = fixedLandmineLocationSelector,
                landminePlanter = Vulture(),
                landmineTracker = DefaultLandmineTracker(),
            )

        val cells = threeByThreeCells
        val board = GameBoard.from(cells)

        `when`("gameBoard 와 countOfLandmines 를 받아") {
            val countOfLandmines = CountOfLandmines(3)
            val result = sut.design(board, countOfLandmines)

            then("countOfLandmines 만큼 지뢰가 설치된 GameBoard2를 반환한다") {
                val candidates =
                    fixedLandmineLocationSelector.selectCandidates(board, countOfLandmines)

                candidates.forEach { candidate ->
                    result.find(candidate).shouldBeTypeOf<Landmine>()
                }
            }

            then("지뢰와 지뢰 주변 셀의 인접 지뢰 갯수도 기록된 gameBoard 를 반환한다") {
                result.area shouldBe board.area
                result.cells shouldBe
                    listOf(
                        Landmine(Location(row = 1, column = 1)),
                        Landmine(Location(row = 1, column = 2)),
                        Landmine(Location(row = 1, column = 3)),
                        BasicCell(Location(row = 2, column = 1), NumberOfAdjacentMines(2)),
                        BasicCell(Location(row = 2, column = 2), NumberOfAdjacentMines(3)),
                        BasicCell(Location(row = 2, column = 3), NumberOfAdjacentMines(2)),
                        BasicCell(Location(row = 3, column = 1), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 2), NumberOfAdjacentMines.ZERO),
                        BasicCell(Location(row = 3, column = 3), NumberOfAdjacentMines.ZERO),
                    )
            }
        }
        `when`("gameBoard 의 전체 사이즈보다 countOfLandmines의 개수가 더 많으면") {
            val countOfLandmines = CountOfLandmines(10)

            then("IllegalArgumentException 예외를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    sut.design(board, countOfLandmines)
                }
            }
        }
    }
})
