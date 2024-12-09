package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CellsTest : BehaviorSpec({

    Given("Cells를 생성할 때") {

        When("높이, 너비, 지뢰 개수를 입력받아 Cells를 생성하면") {
            then("Cells 객체가 정상적으로 생성된다") {
                val height = Height(5)
                val width = Width(4)
                val mineCount = MineCount(3)

                val cells = Cells.generate(height, width, mineCount)

                cells.cells shouldHaveSize (height.value * width.value)
            }
        }

        When("지뢰 개수가 전체 셀 수를 초과하면") {
            then("예외가 발생한다") {
                val height = Height(3)
                val width = Width(3)
                val mineCount = MineCount(10)

                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Cells.generate(height, width, mineCount)
                    }
                exception.message shouldBe "지뢰 개수는 전체 셀 수를 초과할 수 없습니다."
            }
        }
    }

    Given("지뢰 배치 로직") {

        When("특정 지뢰 개수만큼 랜덤으로 배치하면") {
            Then("지뢰 개수와 좌표가 올바르게 설정된다") {
                val height = Height(4)
                val width = Width(4)
                val mineCount = MineCount(5)

                val cells = Cells.generate(height, width, mineCount)
                val mineCells = cells.cells.filterIsInstance<Cell.Mine>()

                mineCells shouldHaveSize mineCount.value

                val mineCoordinates = mineCells.map { it.coordinate }.toSet()
                mineCoordinates shouldHaveSize mineCount.value
            }
        }
    }
})
