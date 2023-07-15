package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellSpec : DescribeSpec(
    {
        describe("기본 셀 생성 검증") {
            it("기본 셀을 생성할 수 있다.") {
                shouldNotThrowAny {
                    Normal(Position(0, 0))
                }
            }
        }

        describe("지뢰 셀 생성 검증") {
            it("지뢰 셀을 생성할 수 있다.") {
                shouldNotThrowAny {
                    Mine(Position(0, 0))
                }
            }
        }

        describe("일반 셀 오픈 검증") {
            context("일반 셀을 오픈하면") {
                val cell = Normal(Position(0, 0)).also { it.open() }

                it("셀이 오픈된다.") {
                    cell.isOpened shouldBe true
                }
            }
        }
    },
)
