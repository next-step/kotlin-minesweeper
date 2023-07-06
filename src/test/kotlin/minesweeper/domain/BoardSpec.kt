package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec

fun cells(vararg cells: Cell): Cells = Cells(cells.toList())

class BoardSpec : DescribeSpec(
    {
        describe("게임 보드 생성 검증 (생성자)") {
            context("cells 가 주어지면") {
                it("보드를 생성할 수 있다.") {
                    shouldNotThrowAny {
                        Board(
                            cells = listOf(
                                cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                                cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                                cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
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
                                cells()
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
                                cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                                cells(Normal(0, 1), Normal(1, 1)),
                            ),
                        )
                    }
                }
            }
        }
    },
)
