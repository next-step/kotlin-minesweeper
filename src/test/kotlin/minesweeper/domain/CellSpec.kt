package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec

class CellSpec : DescribeSpec(
    {
        describe("기본 셀 생성 검증") {
            it("기본 셀을 생성할 수 있다.") {
                shouldNotThrowAny {
                    Normal()
                }
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
