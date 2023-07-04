package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardSpec : DescribeSpec(
    {
        describe("게임 보드 생성 검증 (생성자)") {
            context("cells 가 주어지면") {
                it("보드를 생성할 수 있다.") {
                    shouldNotThrowAny {
                        Board(
                            cells = listOf(
                                Cells.normal(3),
                                Cells.normal(3),
                            ),
                        )
                    }
                }
            }

            context("높이가 0인 cells 가 주어지면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = emptyList(),
                        )
                    }
                }
            }

            context("너비가 0인 cells 가 주어지면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = listOf(
                                Cells.normal(0),
                            ),
                        )
                    }
                }
            }

            context("각 행의 너비(셀 개수)가 다르면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = listOf(
                                Cells.normal(3),
                                Cells.normal(4),
                            ),
                        )
                    }
                }
            }
        }

        describe("게임 보드 생성 검증 (of)") {
            context("높이, 너비, 지뢰 개수가 주어지면") {
                it("보드를 생성할 수 있다.") {
                    shouldNotThrowAny {
                        Board.of(
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
                val board = Board.of(
                    height = height,
                    width = width,
                    mineCount = mineCount,
                )

                it("보드 내 지뢰의 수는 5개이다.") {
                    var mineCellCount = 0
                    board.cells.forEach { row ->
                        mineCellCount += row.values.filterIsInstance<Mine>().size
                    }

                    mineCellCount shouldBe 5
                }

                it("보드 내 지뢰가 아닌 셀의 수는 20개이다.") {
                    var emptyCellCount = 0
                    board.cells.forEach { row ->
                        emptyCellCount += row.values.filterIsInstance<Normal>().size
                    }

                    emptyCellCount shouldBe 20
                }
            }
        }
    },
)
