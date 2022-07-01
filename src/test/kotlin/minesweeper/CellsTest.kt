package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.CellsOpenResult
import minesweeper.domain.Coordinate

class CellsTest : DescribeSpec({

    describe("isAllBlockOpened") {
        context("Cell 에 Block 이 존재하는 경우") {
            it("모두 열려있다면 true 를 리턴한다.") {
                val cells = Cells(listOf(OpenedCell(Coordinate(0, 0)), OpenedCell(Coordinate(0, 1))))

                cells.isAllBlockOpened() shouldBe true
            }
            it("모두 열려있지 않으면 false 를 리턴한다.") {
                val cells = Cells(listOf(OpenedCell(Coordinate(0, 0)), Cell.Block(Coordinate(0, 1))))

                cells.isAllBlockOpened() shouldBe false
            }
        }
    }

    describe("openAllMine") {
        context("보드에 존재하는 모든 지뢰를") {
            it("open 상태로 바꾼다.") {
                val cells = Cells(listOf(Cell.Mine(Coordinate(0, 0)), Cell.Mine(Coordinate(0, 1))))

                cells.openAllMine()

                cells[0].isOpened() shouldBe true
                cells[1].isOpened() shouldBe true
            }
        }
    }

    describe("openCell") {
        context("Cells 에서 하나의 좌표를 열면") {
            it("연결되어 있으면서 지뢰가 없는 좌표가 전부 열린다.") {
                /* 보드 Cell 배치
                0 1 *
                1 2 1
                1 * 1
                */
                val cells = Cells(
                    listOf(
                        Cell.Block(Coordinate(0, 0), 0),
                        Cell.Block(Coordinate(0, 1), 1),
                        Cell.Mine(Coordinate(0, 1)),
                        Cell.Block(Coordinate(1, 0), 1),
                        Cell.Block(Coordinate(1, 1), 2),
                        Cell.Block(Coordinate(1, 2), 1),
                        Cell.Block(Coordinate(2, 0), 1),
                        Cell.Mine(Coordinate(2, 1)),
                        Cell.Block(Coordinate(2, 2), 1)
                    )
                )

                cells.open(Coordinate(0, 0))

                cells.count { it.isOpened() } shouldBe 4
                cells shouldContainAll listOf(
                    OpenedCell(Coordinate(0, 0), 0),
                    OpenedCell(Coordinate(1, 0), 1),
                    OpenedCell(Coordinate(0, 1), 1),
                    OpenedCell(Coordinate(1, 1), 2)
                )
            }
        }

        context("좌표를 열었을 때 지뢰가 아니라면") {
            it("성공한 것을 알린다. ( CellsOpenResult.Success 을 리턴한다. )") {
                val cells = Cells(listOf(Cell.Block(Coordinate(0, 0))))

                cells.open(Coordinate(0, 0)) shouldBe CellsOpenResult.Success
            }
        }

        context("좌표를 열었을 때 지뢰가 아니고 이미 열려있다면") {
            it("CellsOpenResult.AlreadyOpened 을 리턴한다.") {
                val cells = Cells(listOf(OpenedCell(Coordinate(0, 0))))

                cells.open(Coordinate(0, 0)) shouldBe CellsOpenResult.AlreadyOpened
            }
        }

        context("좌표를 열었을 때 지뢰라면") {
            it("실패한 것을 알린다. ( CellsOpenResult.Fail 을 리턴한다. )") {
                val cells = Cells(listOf(Cell.Mine(Coordinate(0, 0))))

                cells.open(Coordinate(0, 0)) shouldBe CellsOpenResult.Fail
            }
        }

        context("좌표가 보드판을 벗어났다면") {
            it("CellsOpenResult.NotFound 를 리턴한다.") {
                val cells = Cells(listOf(Cell.Block(Coordinate(0, 0))))

                cells.open(Coordinate(0, 1)) shouldBe CellsOpenResult.NotFound
            }
        }
    }
})
