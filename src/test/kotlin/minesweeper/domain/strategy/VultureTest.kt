package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location

class VultureTest : BehaviorSpec({
    given("Vulture 는") {
        val sut = Vulture()

        `when`("전체 셀 리스트와 지뢰 지역 정보 목록을 받아") {
            val allCells =
                Cells(
                    ClosedCell(Location(row = 1, column = 1)),
                    ClosedCell(Location(row = 1, column = 2)),
                    ClosedCell(Location(row = 1, column = 3)),
                    ClosedCell(Location(row = 2, column = 1)),
                    ClosedCell(Location(row = 2, column = 2)),
                    ClosedCell(Location(row = 2, column = 3)),
                    ClosedCell(Location(row = 3, column = 1)),
                    ClosedCell(Location(row = 3, column = 2)),
                    ClosedCell(Location(row = 3, column = 3)),
                )

            val landmineCandidates =
                listOf(
                    Location(row = 1, column = 1),
                    Location(row = 2, column = 2),
                    Location(row = 3, column = 3),
                )

            val result = sut.plantAll(allCells = allCells, landmineCandidates = landmineCandidates)

            then("지뢰를 심은 셀 리스트를 반환한다") {
                val expected =
                    listOf(
                        ClosedCell(location = Location(row = 1, column = 1), hasLandmine = true),
                        ClosedCell(Location(row = 1, column = 2)),
                        ClosedCell(Location(row = 1, column = 3)),
                        ClosedCell(Location(row = 2, column = 1)),
                        ClosedCell(location = Location(row = 2, column = 2), hasLandmine = true),
                        ClosedCell(Location(row = 2, column = 3)),
                        ClosedCell(Location(row = 3, column = 1)),
                        ClosedCell(Location(row = 3, column = 2)),
                        ClosedCell(location = Location(row = 3, column = 3), hasLandmine = true),
                    )

                result shouldBe expected
            }
        }
    }
})
