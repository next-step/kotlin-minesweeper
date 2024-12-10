package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class MineFieldTest : BehaviorSpec({

    Given("MineField 객체를 생성할 때") {

        When("유효한 높이, 너비, 지뢰 개수를 입력하면") {
            Then("MineField 객체가 정상적으로 생성된다") {
                val height = Height(5)
                val width = Width(4)
                val mineCount = MineCount(6)

                val mineField = MineField.create(height, width, mineCount)

                mineField.cells shouldHaveSize (height.value * width.value)
            }
        }

        When("지뢰 개수가 전체 셀 수를 초과하면") {
            Then("IllegalArgumentException이 발생한다") {
                val height = Height(3)
                val width = Width(3)
                val mineCount = MineCount(10)

                val exception =
                    shouldThrow<IllegalArgumentException> {
                        MineField.create(height, width, mineCount)
                    }

                exception.message shouldBe "지뢰 개수는 전체 셀 수를 초과할 수 없습니다."
            }
        }
    }

    Given("MineField 내부 셀 상태를 확인할 때") {

        When("Cells 객체를 통해 모든 셀을 가져오면") {
            Then("총 셀 개수는 높이 x 너비와 같아야 한다") {
                val height = Height(4)
                val width = Width(5)
                val mineCount = MineCount(8)

                val mineField = MineField.create(height, width, mineCount)
                val allCells = mineField.cells

                allCells shouldHaveSize (height.value * width.value)
            }
        }

        When("Cells 객체에서 지뢰 셀을 필터링하면") {
            Then("지뢰 셀의 개수는 입력된 지뢰 개수와 같아야 한다") {
                val height = Height(6)
                val width = Width(6)
                val mineCount = MineCount(10)

                val mineField = MineField.create(height, width, mineCount)
                val mineCells = mineField.cells.filterIsInstance<Cell.Mine>()

                mineCells shouldHaveSize mineCount.value
            }
        }
    }
})
