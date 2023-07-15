package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardGeneratorSpec : DescribeSpec({
    describe("게임 보드 생성 검증") {
        context("높이, 너비, 지뢰 개수가 주어지면") {
            it("보드를 생성할 수 있다.") {
                shouldNotThrowAny {
                    BoardGenerator(
                        height = PositiveInt(5),
                        width = PositiveInt(5),
                        mineCount = PositiveInt(5),
                    ).generate()
                }
            }
        }
    }

    describe("마인 개수 검증 (5x5 보드)") {
        val height = PositiveInt(5)
        val width = PositiveInt(5)

        context("지뢰 개수 5개로 생성하면") {
            val mineCount = PositiveInt(5)
            val board = BoardGenerator(
                height = height,
                width = width,
                mineCount = mineCount,
            ).generate()

            it("보드 내 지뢰의 수는 5개이다.") {
                var mineCellCount = 0
                board.cells.forEach { row ->
                    mineCellCount += row.filterIsInstance<Mine>().size
                }

                mineCellCount shouldBe 5
            }

            it("보드 내 지뢰가 아닌 셀의 수는 20개이다.") {
                var emptyCellCount = 0
                board.cells.forEach { row ->
                    emptyCellCount += row.filterIsInstance<Normal>().size
                }

                emptyCellCount shouldBe 20
            }
        }
    }

    describe("주변 마인 개수 계산 검증 (3x3 보드)") {
        val board = BoardGenerator(
            height = PositiveInt(3),
            width = PositiveInt(3),
            mineCount = PositiveInt(3),
        ).generate(minePositions = listOf(Position(0, 0), Position(0, 1), Position(0, 2)))

        it("주변의 지뢰 수 만큼 주변 마인 개수가 설정된다.") {
            (board.cells[0][1] as Normal).adjacentMineCount shouldBe 2
            (board.cells[0][2] as Normal).adjacentMineCount shouldBe 0
            (board.cells[1][1] as Normal).adjacentMineCount shouldBe 3
            (board.cells[1][2] as Normal).adjacentMineCount shouldBe 0
            (board.cells[2][1] as Normal).adjacentMineCount shouldBe 2
            (board.cells[2][2] as Normal).adjacentMineCount shouldBe 0
        }
    }
})
