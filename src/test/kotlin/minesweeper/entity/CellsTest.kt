package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CellsTest : BehaviorSpec({

    Given("Cells를 생성할 때") {

        When("List<Cell>을 입력받아 Cells를 생성하면") {
            Then("Cells 객체가 정상적으로 생성된다") {
                val cellsList =
                    List(20) { index ->
                        val x = index % 4
                        val y = index / 4
                        if ((x + y) % 2 == 0) {
                            Cell.Mine(Coordinate(x, y))
                        } else {
                            Cell.Empty(Coordinate(x, y))
                        }
                    }

                val cells = Cells(cellsList)

                cells.cells shouldHaveSize 20
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
