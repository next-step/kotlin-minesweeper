package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.BasicCellGridTextFixture.threeByThreeGrid

class RowsTest : BehaviorSpec({
    given("Rows 는") {
        val grid = threeByThreeGrid
        val sut = Rows(grid.map { Row(it) })

        `when`("Location 으로 해당 위치의 Cell 을 찾으면") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 Cell 을 반환한다") {
                result shouldNotBe null
                result?.location() shouldBe location
                result shouldBe grid[location.row - 1][location.column - 1]
            }
        }
        `when`("Location 이 해당 Rows가 가진 row 범위를 벗어나면") {
            then("IllegalArgumentException 예외를 던진다") {
                val locations =
                    listOf(
                        Location(row = 4, column = 1),
                        Location(row = 4, column = 2),
                        Location(row = 4, column = 3),
                    )
                locations.forEach {
                    shouldThrow<IllegalArgumentException> { sut.find(it) }
                }
            }
        }

        `when`("Location 으로 row 범위 내에서 cell을 찾지 못하면") {
            then("null 을 반환한다") {
                val locations =
                    listOf(
                        Location(row = 1, column = 0),
                        Location(row = 1, column = 4),
                        Location(row = 2, column = 0),
                        Location(row = 2, column = 4),
                        Location(row = 3, column = 0),
                        Location(row = 3, column = 4),
                    )
                locations.forEach {
                    sut.find(it) shouldBe null
                }
            }
        }
    }
})
