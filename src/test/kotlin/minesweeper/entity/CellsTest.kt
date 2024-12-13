package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class CellsTest : BehaviorSpec({

    Given("Cells를 생성할 때") {
        When("전체 좌표와 지뢰 좌표가 주어지면") {
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

            Then("지뢰와 빈 셀이 올바르게 생성된다") {
                val cells = Cells.create(allCoordinates, mineCoordinates)

                cells.cells shouldHaveSize allCoordinates.size
                cells.cells.filterIsInstance<Cell.Mine>() shouldHaveSize mineCoordinates.size
                cells.cells.filterIsInstance<Cell.Empty>() shouldHaveSize (allCoordinates.size - mineCoordinates.size)
            }
        }

        When("지뢰 좌표가 전체 좌표에 포함되지 않는 경우") {
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

            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Cells.create(allCoordinates, invalidMineCoordinates)
                    }
                exception.message shouldBe "지뢰 좌표가 보드 범위를 벗어났습니다"
            }
        }

        When("전체 좌표가 비어있는 경우") {
            val allCoordinates = emptyList<Coordinate>()
            val mineCoordinates = emptySet<Coordinate>()

            Then("빈 컬렉션이 생성된다") {
                val cells = Cells.create(allCoordinates, mineCoordinates)
                cells.cells shouldHaveSize 0
            }
        }
    }

    Given("셀 컬렉션에서 특정 좌표를 검색할 때") {

        val cellsList =
            mapOf(
                Coordinate(0, 0) to Cell.Mine(Coordinate(0, 0)),
                Coordinate(1, 0) to Cell.Empty(Coordinate(1, 0)),
                Coordinate(0, 1) to Cell.Mine(Coordinate(0, 1)),
                Coordinate(1, 1) to Cell.Empty(Coordinate(1, 1)),
            )
        val cells = Cells(cellsList)

        When("해당 좌표에 셀이 존재하면") {
            val coordinate = Coordinate(1, 0)

            Then("올바른 셀을 반환해야 한다") {
                val cell = cells.findCell(coordinate)

                cell.coordinate shouldBe coordinate
                cell.shouldBeTypeOf<Cell.Empty>()
            }
        }

        When("해당 좌표에 지뢰 셀이 존재하면") {
            val coordinate = Coordinate(0, 0)

            Then("지뢰 셀을 반환해야 한다") {
                val cell = cells.findCell(coordinate)

                cell.coordinate shouldBe coordinate
                cell.shouldBeTypeOf<Cell.Mine>()
            }
        }

        When("해당 좌표에 셀이 존재하지 않으면") {
            val invalidCoordinate = Coordinate(2, 2)

            Then("예외를 발생시킨다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        cells.findCell(invalidCoordinate)
                    }
                exception.message shouldBe "셀을 찾을 수 없습니다: $invalidCoordinate"
            }
        }
    }
    Given("셀 컬렉션에서 지뢰가 열렸는지 확인할 때") {
        val cellsList =
            mapOf(
                Coordinate(0, 0) to Cell.Mine(Coordinate(0, 0)),
                Coordinate(1, 0) to Cell.Empty(Coordinate(1, 0)),
                Coordinate(0, 1) to Cell.Mine(Coordinate(0, 1)),
                Coordinate(1, 1) to Cell.Empty(Coordinate(1, 1)),
            )
        val cells = Cells(cellsList)
        When("지뢰가 열리지 않았으면") {
            Then("false를 반환한다") {
                cells.hasRevealedMine() shouldBe false
            }
        }

        When("지뢰가 열렸으면") {
            cells.findCell(Coordinate(0, 0)).open()
            Then("true를 반환한다") {
                cells.hasRevealedMine() shouldBe true
            }
        }
    }
})
