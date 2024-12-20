package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberCell
import minesweeper.domain.cell.NumberOfAdjacentMines

class OpenCellStrategyHandlerTest : BehaviorSpec({
    val sut = OpenCellStrategyHandler()

    given("대상 셀이 이미 열린 셀일 때") {
        val targetCell = NumberCell(Location(row = 1, column = 1), NumberOfAdjacentMines.ZERO)

        `when`("셀 오픈 전략을 찾으면") {
            val result = sut.findStrategy(targetCell)

            then("무변화 셀 오픈 전략을 반환한다") {
                result.shouldBeInstanceOf<NoChangeOpenCellStrategy>()
            }
        }
    }

    given("대상 셀이 닫혀있고 지뢰를 갖고 있을 때") {
        val targetCell =
            ClosedCell(
                location = Location(row = 1, column = 1),
                hasLandmine = true,
            )

        `when`("셀 오픈 전략을 찾으면") {
            val result = sut.findStrategy(targetCell)

            then("단일 셀 오픈 전략을 반환한다") {
                result.shouldBeInstanceOf<SingleOpenCellStrategy>()
            }
        }
    }

    given("대상 셀이 닫혀있고 인접 지뢰 개수가 0보다 클 때") {
        val targetCell =
            ClosedCell(
                location = Location(row = 1, column = 1),
                numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            )

        `when`("셀 오픈 전략을 찾으면") {
            val result = sut.findStrategy(targetCell)

            then("단일 셀 오픈 전략을 반환한다") {
                result.shouldBeInstanceOf<SingleOpenCellStrategy>()
            }
        }
    }

    given("대상 셀이 닫혀있고 인접 지뢰 개수가 0일 때") {
        val targetCell = ClosedCell(location = Location(row = 1, column = 1))

        `when`("셀 오픈 전략을 찾으면") {
            val result = sut.findStrategy(targetCell)

            then("단일 셀 오픈 전략을 반환한다") {
                result.shouldBeInstanceOf<CascadingOpenCellStrategy>()
            }
        }
    }
})
