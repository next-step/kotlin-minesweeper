package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CellsTest : BehaviorSpec({

    Given("Cells를 생성할 때") {

        When("전체 좌표와 지뢰 좌표가 주어지면") {
            Then("지뢰와 빈 셀이 올바르게 생성된다") {
                val allCoordinates =
                    listOf(
                        Coordinate(0, 0),
                        Coordinate(1, 0),
                        Coordinate(0, 1),
                        Coordinate(1, 1),
                    )

                val mineCoordinates =
                    setOf(
                        Coordinate(0, 0),
                        Coordinate(1, 1),
                    )

                val cells = Cells.create(allCoordinates, mineCoordinates)

                cells.cells shouldHaveSize allCoordinates.size

                cells.cells.filterIsInstance<Cell.Mine>() shouldBe
                    listOf(
                        Cell.Mine(Coordinate(0, 0)),
                        Cell.Mine(Coordinate(1, 1)),
                    )

                cells.cells.filterIsInstance<Cell.Empty>() shouldBe
                    listOf(
                        Cell.Empty(Coordinate(1, 0)),
                        Cell.Empty(Coordinate(0, 1)),
                    )
            }
        }

        When("지뢰 좌표가 전체 좌표에 포함되지 않는 경우") {
            Then("예외가 발생한다") {
                val allCoordinates =
                    listOf(
                        Coordinate(0, 0),
                        Coordinate(1, 0),
                        Coordinate(0, 1),
                        Coordinate(1, 1),
                    )

                val invalidMineCoordinates =
                    setOf(
                        Coordinate(2, 2),
                    )

                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Cells.create(allCoordinates, invalidMineCoordinates)
                    }

                exception.message shouldBe "지뢰 좌표가 보드 범위를 벗어났습니다"
            }
        }

        When("전체 좌표가 비어있는 경우") {
            Then("빈 컬렉션이 생성된다") {
                val allCoordinates = emptyList<Coordinate>()
                val mineCoordinates = emptySet<Coordinate>()

                val cells = Cells.create(allCoordinates, mineCoordinates)

                cells.cells shouldHaveSize 0
            }
        }
    }

    Given("셀 컬렉션에서 특정 좌표를 검색할 때") {

        val cellsList =
            listOf(
                Cell.Mine(Coordinate(0, 0)),
                Cell.Empty(Coordinate(1, 0)),
                Cell.Mine(Coordinate(0, 1)),
                Cell.Empty(Coordinate(1, 1)),
            )
        val cells = Cells(cellsList)

        When("해당 좌표에 셀이 존재하면") {
            Then("올바른 셀을 반환해야 한다") {
                val coordinate = Coordinate(1, 0)
                val cell = cells.findCell(coordinate)

                cell shouldBe Cell.Empty(coordinate)
            }
        }

        When("해당 좌표에 지뢰 셀이 존재하면") {
            Then("지뢰 셀을 반환해야 한다") {
                val coordinate = Coordinate(0, 0)
                val cell = cells.findCell(coordinate)

                cell shouldBe Cell.Mine(coordinate)
            }
        }

        When("해당 좌표에 셀이 존재하지 않으면") {
            Then("예외를 발생시킨다") {
                val invalidCoordinate = Coordinate(2, 2)

                val exception =
                    shouldThrow<IllegalArgumentException> {
                        cells.findCell(invalidCoordinate)
                    }
                exception.message shouldBe "셀을 찾을 수 없습니다: $invalidCoordinate"
            }
        }
    }
})
