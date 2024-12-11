package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
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
            forAll(
                row(Height(3), Width(3), MineCount(10)),
                row(Height(3), Width(3), MineCount(9)),
            ) { height, width, mineCount ->
                Then("예외가 발생한다") {
                    val factory = MineFieldFactory(fixedGenerator)
                    val exception =
                        shouldThrow<IllegalArgumentException> {
                            factory.create(
                                height = height,
                                width = width,
                                mineCount = mineCount,
                            )
                        }
                    exception.message shouldContain "지뢰 개수는 전체 셀 수를 초과할 수 없습니다."
                }
            }
        }
    }
})
