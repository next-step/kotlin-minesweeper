package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec

class CellsSpec : DescribeSpec({
    describe("게임 보드 생성 검증 (생성자)") {
        context("양의 높이와 양의 일정 너비를 가진 cell 목록이 주어지면") {
            it("Cells 를 생성할 수 있다.") {
                shouldNotThrowAny {
                    Cells(
                        listOf(
                            listOf(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                            listOf(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                            listOf(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
                        ),
                    )
                }
            }
        }

        context("높이가 0인 cells 가 주어지면") {
            it("Cells 를 생성할 수 없다.") {
                shouldThrowExactly<IllegalArgumentException> {
                    Cells()
                }
            }
        }

        context("너비가 0인 cells 가 주어지면") {
            it("Cells 를 생성할 수 없다.") {
                shouldThrowExactly<IllegalArgumentException> {
                    Cells(
                        listOf(
                            emptyList(),
                        ),
                    )
                }
            }
        }

        context("각 행의 너비(셀 개수)가 다르면") {
            it("Cells 를 생성할 수 없다.") {
                shouldThrowExactly<IllegalArgumentException> {
                    Cells(
                        listOf(
                            listOf(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                            listOf(Normal(0, 1), Normal(1, 1)),
                        ),
                    )
                }
            }
        }
    }
})
