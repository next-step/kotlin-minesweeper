package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.BasicCellGridTextFixture.threeByThreeGrid

class RowsTest : BehaviorSpec({
    given("Rows 는") {
        val grid = threeByThreeGrid
        val sut = Rows(grid.map { Row(it) })

        `when`("Location 으로 해당 위치의 Cell 을 찾으면") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 Cell 을 반환한다") {
                result.location() shouldBe location
                result shouldBe grid[location.row - 1][location.column - 1]
            }
        }
        `when`("Location 으로 해당 위치의 Cell을 찾지 못하면") {
            then("IllegalArgumentException 예외를 던진다") {
                val locations =
                    listOf(
                        Location(row = 4, column = 3),
                        Location(row = 4, column = 4),
                    )
                locations.forEach {
                    shouldThrow<IllegalArgumentException> { sut.find(it) }
                }
            }
        }
    }
})
