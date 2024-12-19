package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.oneByOneLocation
import minesweeper.domain.oneByThreeLocation
import minesweeper.domain.oneByTwoLocation
import minesweeper.domain.threeByOneLocation
import minesweeper.domain.threeByThreeCells
import minesweeper.domain.threeByThreeLocation
import minesweeper.domain.threeByTwoLocation
import minesweeper.domain.twoByOneLocation
import minesweeper.domain.twoByThreeLocation
import minesweeper.domain.twoByTwoLocation

class VultureTest : BehaviorSpec({
    given("3x3의 닫힌 셀들과 (1,1), (2,2), (3,3)의 지뢰 위치들을 받아") {
        val allCells = Cells(threeByThreeCells)

        val landmineCandidates = listOf(oneByOneLocation, twoByTwoLocation, threeByThreeLocation)

        val sut = Vulture()

        `when`("지뢰를 전부 매설하면") {
            val result = sut.plantAll(allCells = allCells, landmineCandidates = landmineCandidates)

            then("(1,1), (2,2), (3,3) 에 지뢰를 매설한 닫힌 셀 리스트를 받는다") {
                val expected =
                    listOf(
                        ClosedCell(location = oneByOneLocation, hasLandmine = true),
                        ClosedCell(oneByTwoLocation),
                        ClosedCell(oneByThreeLocation),
                        ClosedCell(twoByOneLocation),
                        ClosedCell(location = twoByTwoLocation, hasLandmine = true),
                        ClosedCell(twoByThreeLocation),
                        ClosedCell(threeByOneLocation),
                        ClosedCell(threeByTwoLocation),
                        ClosedCell(location = threeByThreeLocation, hasLandmine = true),
                    )

                result shouldBe expected
            }
        }
    }
})
