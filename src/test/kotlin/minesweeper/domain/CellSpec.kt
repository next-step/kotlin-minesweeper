package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellSpec : DescribeSpec(
    {
        describe("기본 셀 생성 검증") {
            it("기본 셀을 생성할 수 있다.") {
                shouldNotThrowAny {
                    Normal()
                }
            }
        }

        describe("기본 셀 인접 지뢰 개수 증가 검증") {
            it("인접 지뢰 개수 증가하면 개수가 1 증가한다.") {
                val cell = Normal(0)
                cell.increaseAdjacentMineCount()

                cell.adjacentMineCount shouldBe 1
            }
        }

        describe("지뢰 셀 생성 검증") {
            it("지뢰 셀을 생성할 수 있다.") {
                shouldNotThrowAny {
                    Mine()
                }
            }
        }
    },
)
