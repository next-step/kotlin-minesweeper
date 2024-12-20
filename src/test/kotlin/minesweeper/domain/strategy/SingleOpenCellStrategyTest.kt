package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.domain.Cells
import minesweeper.domain.cell.Location
import minesweeper.domain.fiveByFiveCellsWithFiveLandmines
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell

class SingleOpenCellStrategyTest : BehaviorSpec({
    given("원래의 셀 목록과 대상 셀을 받았을 때") {
        val originalCells = Cells(fiveByFiveCellsWithFiveLandmines)
        val targetCell = originalCells.find { it.location == Location(row = 1, column = 5) } ?: throw AssertionError()

        `when`("싱글 셀 오픈 전략을 사용하면") {
            val sut = SingleOpenCellStrategy()
            val result = sut.open(originalCells, targetCell)

            then("해당 셀만 열린 셀 목록을 반환한다") {
                result shouldContainExactlyInAnyOrder fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell
            }
        }
    }
})
