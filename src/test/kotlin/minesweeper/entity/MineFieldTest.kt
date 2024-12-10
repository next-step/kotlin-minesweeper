import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.entity.Cell
import minesweeper.entity.Cells
import minesweeper.entity.Coordinate
import minesweeper.entity.Height
import minesweeper.entity.MineField
import minesweeper.entity.Width

class MineFieldTest : BehaviorSpec({

    Given("MineField 객체를 생성할 때") {

        When("유효한 높이, 너비, 그리고 지뢰 데이터로 생성하면") {
            Then("MineField 객체가 정상적으로 생성된다") {
                val height = Height(5)
                val width = Width(4)

                // 셀 데이터 생성
                val cells =
                    Cells(
                        List(height.value * width.value) { index ->
                            val x = index % width.value
                            val y = index / width.value
                            if ((x + y) % 2 == 0) {
                                Cell.Mine(Coordinate(x, y))
                            } else {
                                Cell.Empty(Coordinate(x, y))
                            }
                        },
                    )

                val mineField = MineField(height, width, cells)

                mineField.cells shouldHaveSize (height.value * width.value)
            }
        }

        When("셀 데이터가 높이 x 너비와 맞지 않으면") {
            Then("예외가 발생한다") {
                val height = Height(3)
                val width = Width(3)

                val invalidCells =
                    Cells(
                        listOf(
                            Cell.Mine(Coordinate(0, 0)),
                            Cell.Empty(Coordinate(1, 0)),
                        ),
                    )

                val exception =
                    shouldThrow<IllegalArgumentException> {
                        MineField(height, width, invalidCells)
                    }

                exception.message shouldBe "Cells의 개수가 보드 크기와 일치하지 않습니다."
            }
        }
    }

    Given("MineField 내부 셀 상태를 확인할 때") {

        val height = Height(4)
        val width = Width(5)
        val cells =
            Cells(
                List(height.value * width.value) { index ->
                    val x = index % width.value
                    val y = index / width.value
                    if ((x + y) % 3 == 0) {
                        Cell.Mine(Coordinate(x, y))
                    } else {
                        Cell.Empty(Coordinate(x, y))
                    }
                },
            )
        val mineField = MineField(height, width, cells)

        When("Cells 객체를 통해 모든 셀을 가져오면") {
            Then("총 셀 개수는 높이 x 너비와 같아야 한다") {
                mineField.cells shouldHaveSize (height.value * width.value)
            }
        }

        When("Cells 객체에서 지뢰 셀을 필터링하면") {
            Then("지뢰 셀의 개수를 확인할 수 있다") {
                val mineCells = mineField.cells.filterIsInstance<Cell.Mine>()
                mineCells shouldHaveSize mineField.cells.count { it is Cell.Mine }
            }
        }
    }
})
