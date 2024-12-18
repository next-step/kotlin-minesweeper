package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.Cells
import minesweeper.domain.cell.BasicCell
import minesweeper.domain.cell.Landmine
import minesweeper.domain.cell.Location

class VultureTest : BehaviorSpec({
    given("Vulture 는") {
        val sut = Vulture()

        `when`("Location을 받아") {
            val location = Location(row = 1, column = 1)
            val result = sut.plant(location)

            then("해당 Location 을 가진 Landmine을 반환한다") {
                result.shouldBeTypeOf<Landmine>()

                result.location() shouldBe location
            }
        }

        `when`("전체 셀 리스트와 지뢰 지역 정보 목록을 받아") {
            val allCells =
                Cells(
                    BasicCell(Location(row = 1, column = 1)),
                    BasicCell(Location(row = 1, column = 2)),
                    BasicCell(Location(row = 1, column = 3)),
                    BasicCell(Location(row = 2, column = 1)),
                    BasicCell(Location(row = 2, column = 2)),
                    BasicCell(Location(row = 2, column = 3)),
                    BasicCell(Location(row = 3, column = 1)),
                    BasicCell(Location(row = 3, column = 2)),
                    BasicCell(Location(row = 3, column = 3)),
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
                        Landmine(Location(row = 1, column = 1)),
                        BasicCell(Location(row = 1, column = 2)),
                        BasicCell(Location(row = 1, column = 3)),
                        BasicCell(Location(row = 2, column = 1)),
                        Landmine(Location(row = 2, column = 2)),
                        BasicCell(Location(row = 2, column = 3)),
                        BasicCell(Location(row = 3, column = 1)),
                        BasicCell(Location(row = 3, column = 2)),
                        Landmine(Location(row = 3, column = 3)),
                    )

                result shouldBe expected
            }
        }
    }
})
