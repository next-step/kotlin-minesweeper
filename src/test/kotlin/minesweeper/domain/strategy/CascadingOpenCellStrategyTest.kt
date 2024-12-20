package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.fiveByFiveCellsWithFiveLandmines
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesOneOneNumberCellAndAdjacentNumberCell

class CascadingOpenCellStrategyTest : BehaviorSpec({
    given("원래의 셀 목록과 대상 셀을 받았을 때") {
        val originalCells = Cells(fiveByFiveCellsWithFiveLandmines)
        val targetCell = originalCells.first()

        `when`("연쇄 셀 오픈 전략을 사용하면") {
            val sut = CascadingOpenCellStrategy()
            val result = sut.open(originalCells, targetCell)

            then("해당 셀을 포함하여 인접한 숫자 셀을 연쇄적으로 오픈한 셀 목록을 반환한다") {
                result shouldBe fiveByFiveCellsWithFiveLandminesOneOneNumberCellAndAdjacentNumberCell
            }
        }
    }
})
