package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class MineFieldFactoryTest : BehaviorSpec({

    Given("MineFieldFactory로 MineField를 생성할 때") {

        val fixedCoordinates =
            setOf(
                Coordinate(0, 0),
                Coordinate(1, 1),
                Coordinate(2, 2),
            )
        val fixedGenerator = FixedMineGenerator(fixedCoordinates)

        When("고정된 지뢰 생성기로 생성하면") {
            Then("고정된 좌표를 포함한 MineField가 생성된다") {
                val factory = MineFieldFactory(fixedGenerator)
                val mineField =
                    factory.create(
                        height = Height(5),
                        width = Width(5),
                        mineCount = MineCount(3),
                    )

                val mineCells = mineField.cells.filterIsInstance<Cell.Mine>()
                val mineCellCoordinates = mineCells.map { it.coordinate }.toSet()

                mineCellCoordinates shouldBe fixedCoordinates
            }
        }

        When("지뢰 개수가 전체 셀 수를 초과하면") {
            Then("예외가 발생한다") {
                val factory = MineFieldFactory(fixedGenerator)
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        factory.create(
                            height = Height(3),
                            width = Width(3),
                            mineCount = MineCount(10),
                        )
                    }
                exception.message shouldContain "지뢰 개수는 전체 셀 수를 초과할 수 없습니다."
            }
        }
    }
})
