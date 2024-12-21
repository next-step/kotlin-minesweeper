package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : DescribeSpec({
    lateinit var mineGameMetric: MineGameMetric
    lateinit var mineBoard: MineBoard
    lateinit var sut: MineSweeperGame

    beforeTest {
        mineGameMetric = MineGameMetric(3, 3, 8)
        val cells =
            Cells(
                listOf(
                    Cell.EmptyCell(Coordinate(Row(2), Col(2))),
                    Cell.MineCell(Coordinate(Row(1), Col(1))),
                    Cell.MineCell(Coordinate(Row(1), Col(2))),
                    Cell.MineCell(Coordinate(Row(1), Col(3))),
                    Cell.MineCell(Coordinate(Row(2), Col(1))),
                    Cell.MineCell(Coordinate(Row(2), Col(3))),
                    Cell.MineCell(Coordinate(Row(3), Col(1))),
                    Cell.MineCell(Coordinate(Row(3), Col(2))),
                    Cell.MineCell(Coordinate(Row(3), Col(3))),
                ),
            )

        mineBoard = MineBoard(mineGameMetric, cells)
        sut = MineSweeperGame(mineBoard)
    }

    describe("isContinueGame test") {
        context("하나 이상의 셀을 지뢰 open 한 경우") {
            it("should be false") {
                sut.isContinueGame().shouldBeFalse()
            }
        }

        context("비어있는 모든 셀을 open 한 경우") {
            it("should be false") {
                sut.isContinueGame().shouldBeFalse()
            }

            it("game result should be success") {
                sut.result shouldBe GameResult.SUCCESS
            }
        }

        context("지뢰 셀을 open 하지 않았고, 비어있는 모든 칸을 open 하지 않은 경우") {
            it("should be true") {
                sut.isContinueGame().shouldBeTrue()
            }
        }
    }
})
