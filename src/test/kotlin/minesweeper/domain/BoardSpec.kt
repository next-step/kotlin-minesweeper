package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardSpec : DescribeSpec(
    {
        describe("게임 보드 생성 검증") {
            context("높이, 너비, 지뢰 개수가 주어지면") {
                it("보드를 생성할 수 있다.") {
                    shouldNotThrowAny {
                        Board(
                            height = PositiveInt(5),
                            width = PositiveInt(5),
                            mineCount = PositiveInt(5),
                        )
                    }
                }
            }
        }

        describe("마인 개수 검증 (5x5 보드)") {
            val height = PositiveInt(5)
            val width = PositiveInt(5)

            context("지뢰 개수 5개로 생성하면") {
                val mineCount = PositiveInt(5)
                val board = Board(
                    height = height,
                    width = width,
                    mineCount = mineCount,
                )

                it("보드 내 지뢰의 수는 5개이다.") {
                    var mineCellCount = 0
                    board.cells.forEach { row ->
                        mineCellCount += row.values.filter { it.isMine }.size
                    }

                    mineCellCount shouldBe 5
                }

                it("보드 내 지뢰가 아닌 셀의 수는 20개이다.") {
                    var emptyCellCount = 0
                    board.cells.forEach { row ->
                        emptyCellCount += row.values.filter { !it.isMine }.size
                    }

                    emptyCellCount shouldBe 20
                }
            }
        }
    },
)
